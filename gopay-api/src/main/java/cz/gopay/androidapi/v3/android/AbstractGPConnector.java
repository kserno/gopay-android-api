package cz.gopay.androidapi.v3.android;

import java.util.logging.Level;
import java.util.logging.Logger;

import cz.gopay.androidapi.v3.AuthClient;
import cz.gopay.androidapi.v3.exception.GPClientException;
import cz.gopay.androidapi.v3.GPExceptionHandler;
import cz.gopay.androidapi.v3.IGPConnector;
import cz.gopay.androidapi.v3.PaymentClient;
import cz.gopay.androidapi.v3.exception.WebClientException;
import cz.gopay.androidapi.v3.model.access.AccessToken;
import cz.gopay.androidapi.v3.model.access.AuthHeader;
import cz.gopay.androidapi.v3.model.access.OAuth;
import cz.gopay.androidapi.v3.model.payment.BasePayment;
import cz.gopay.androidapi.v3.model.payment.NextPayment;
import cz.gopay.androidapi.v3.model.payment.Payment;
import cz.gopay.androidapi.v3.model.payment.PaymentResult;

/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 * @author František Sichinger
 */
public abstract class AbstractGPConnector implements IGPConnector {

    protected static final Logger logger = Logger.getLogger("GPConnector");

    public static int CONNECTION_POOL_SIZE = 1;
    public static int CONNECTION_SETUP_TO = 1;
    public static int CONNECTION_SERVICE_TO = 1;


    protected String apiUrl;

    protected AccessToken accessToken;

    public AbstractGPConnector(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public AbstractGPConnector(String apiUrl, AccessToken token) {
        this(apiUrl);
        this.accessToken = token;
    }

    protected abstract <T> T createRESTClientProxy(String apiUrl, Class<T> proxy);

    @Override
    public IGPConnector getAppToken(String clientId, String clientCredentials) throws GPClientException {
        return getAppToken(clientId, clientCredentials, OAuth.SCOPE_PAYMENT_CREATE);
    }

    @Override
    public IGPConnector getAppToken(String clientId, String clientCredentials, String scope) throws GPClientException {
        try {
            logger.log(Level.INFO,getClass().getSimpleName() + ": get-token [" + clientId + "]");

            AuthClient simple = createRESTClientProxy(apiUrl, AuthClient.class);

            accessToken = simple.loginApplication(AuthHeader.build(clientId, clientCredentials),
                    OAuth.GRANT_TYPE_CLIENT_CREDENTIALS, scope != null ? scope : OAuth.SCOPE_PAYMENT_ALL);

            logger.log(Level.INFO,getClass().getSimpleName() + ": get-token [" + clientId + "] -> [" + accessToken.getAccessToken() + "]");

        } catch (WebClientException e) {
            logger.log(Level.SEVERE,getClass().getSimpleName() + ": get-token Error [" + clientId + "] RC ["
                    + e.getCode(), e);
            GPExceptionHandler.handleException(e);
        }

        return this;
    }

    @Override
    public Payment createPayment(BasePayment payment) throws GPClientException {
        try {
            logger.log(Level.INFO,getClass().getSimpleName() + ": create-payment payer[" + payment.getPayer() + "] -> ["
                    + payment.getTarget() + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.createPayment(AuthHeader
                    .build(accessToken != null ? accessToken.getAccessToken() : null),
                    payment);

        } catch (WebClientException e) {
            logger.log(Level.SEVERE, getClass().getSimpleName() + ": create-payment Error [" + payment.getPayer() + "] -> ["
                            + payment.getTarget() + "] RC [" + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult refundPayment(Long id, Long amount) throws GPClientException {
        try {
            logger.log(Level.INFO, getClass().getSimpleName() + ": refund-payment [" + id + "] amnt[" + amount + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            PaymentResult r = paymentClient
                    .refundPayment(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id, amount);
            return r;
        } catch (WebClientException e) {
            logger.log(Level.SEVERE, getClass().getSimpleName() + ": refund-payment Error [" + id + "] amnt[" + amount + "] RC ["
                    + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public Payment createRecurrentPayment(Long id, NextPayment nextPayment) throws GPClientException {
        try {
            logger.log(Level.INFO, getClass().getSimpleName() + ": create-recurrent - parent id[" + id + "] ["
                    + nextPayment.getOrderNumber() + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.createRecurrentPayment(
                    AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id, nextPayment);

        } catch (WebClientException e) {
            logger.log(Level.SEVERE,
                    getClass().getSimpleName() + ": create-recurrent Error parent id[" + id + "] [" + nextPayment
                            .getOrderNumber()
                            + "] RC [" + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult voidRecurrency(Long id) throws GPClientException {
        try {
            logger.log(Level.INFO, getClass().getSimpleName() + ": void-recurrency parent id [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.voidRecurrence(AuthHeader
                    .build(accessToken != null ? accessToken.getAccessToken() : null),
                    id);

        } catch (WebClientException e) {
            logger.log(Level.SEVERE,getClass().getSimpleName() + ": void recurrency Error parent id[" + id + "] RC ["
                    + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult capturePayment(Long id) throws GPClientException {
        try {
            logger.log(Level.INFO, getClass().getSimpleName() + ": capture payment [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient.capturePayment(AuthHeader
                    .build(accessToken != null ? accessToken.getAccessToken() : null),
                    id);

        } catch (WebClientException e) {
            logger.log(Level.SEVERE, getClass().getSimpleName() + ": capture payment Error [" + id + "] RC ["
                    + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public PaymentResult voidAuthorization(Long id) throws GPClientException {
        try {
            logger.log(Level.INFO, getClass().getSimpleName() + ": void auth payment [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient
                    .voidAuthorization(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id);

        } catch (WebClientException e) {
            logger.log(Level.SEVERE, getClass().getSimpleName() + ": void auth payment Error [" + id + "] RC ["
                    + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public Payment paymentStatus(Long id) throws GPClientException {
        try {
            logger.log(Level.INFO, getClass().getSimpleName() + ": payment-status [" + id + "]");

            PaymentClient paymentClient = createRESTClientProxy(apiUrl, PaymentClient.class);

            return paymentClient
                    .getPayment(AuthHeader.build(accessToken != null ? accessToken.getAccessToken() : null), id);

        } catch (WebClientException e) {
            logger.log(Level.SEVERE, getClass().getSimpleName() + ": payment-status Error [" + id + "] RC ["
                    + e.getCode() + "] Ex: " + e.getError().getErrorMessages(), e);
            GPExceptionHandler.handleException(e);
        }

        return null;
    }

    @Override
    public String getApiUrl() {
        return apiUrl;
    }

    @Override
    public AccessToken getAccessToken() {
        return accessToken;
    }
}
