package cz.gopay.api.v3;


import junit.framework.Assert;

import java.util.Arrays;
import java.util.Calendar;

import cz.gopay.androidapi.v3.IGPConnector;
import cz.gopay.androidapi.v3.exception.GPClientException;
import cz.gopay.androidapi.v3.model.ErrorElement;
import cz.gopay.androidapi.v3.model.access.OAuth;
import cz.gopay.androidapi.v3.model.common.Currency;
import cz.gopay.androidapi.v3.model.payment.BasePayment;
import cz.gopay.androidapi.v3.model.payment.BasePaymentBuilder;
import cz.gopay.androidapi.v3.model.payment.Lang;
import cz.gopay.androidapi.v3.model.payment.Payment;
import cz.gopay.androidapi.v3.model.payment.PaymentFactory;
import cz.gopay.androidapi.v3.model.payment.PaymentResult;
import cz.gopay.androidapi.v3.model.payment.support.Payer;
import cz.gopay.androidapi.v3.model.payment.support.PayerBuilder;
import cz.gopay.androidapi.v3.model.payment.support.PaymentInstrument;
import cz.gopay.androidapi.v3.model.payment.support.Recurrence;
import cz.gopay.androidapi.v3.model.payment.support.RecurrenceCycle;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

/**
 *
 * @author František Sichinger
 */
public class AbstractAndroidTest extends TestCase {

    private static final Logger logger = Logger.getLogger(String.valueOf(AbstractAndroidTest.class));
    public static final String API_URL = "https://gw.sandbox.gopay.com/api";
    public static final String CLIENT_ID = "1744960415";
    public static final String CLIENT_SECRET = "h9wyRz2s";
    public static final Long GOID = 8339303643L;

    private BasePayment createTestBasePayment() {
        String url = "https://www.eshop123.cz/";

        Payer payer = new PayerBuilder().withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
                .addAllowedSwift("FIOBCZPP").build();
        BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
        return builder.withCallback(url+"success", url+"fail", url+"notify", url+"return")
                .order("123", 10L, Currency.EUR, "description")
                .inLang(Lang.EN)
                .addAdditionalParameter("AKey2", "AValue")
                .addItem("An item", 1L, 1L, 1L)
                .toEshop(GOID)
                .payer(payer).build();
    }

    protected long testConnectorCreatePayment(IGPConnector connector) {
        Payment payment = null;
        try {
            BasePayment createpayment = createTestBasePayment();
            payment = connector.getAppToken(CLIENT_ID, CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            handleException(e);
        }
        Assert.assertNotNull(payment.getId());
        Assert.assertTrue(payment.getId() > 0);
        return payment.getId();
    }

    protected void testPaymentStatus(IGPConnector connector) {
        long id = testConnectorCreatePayment(connector);
        Payment payment = null;
        try {
            payment = connector.getAppToken(CLIENT_ID, CLIENT_SECRET).paymentStatus(id);

        } catch (GPClientException e) {
            handleException(e);
        }

        Assert.assertNotNull(payment.getId());
    }

    protected void testPaymentRefund(IGPConnector connector) {
        try {
            PaymentResult refundPayment = connector.
                    getAppToken(CLIENT_ID, CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).
                    refundPayment(3000027082L, 2L);
            Assert.assertTrue(refundPayment.getId() == 3000027082L);
        } catch (GPClientException ex) {
            handleException(ex);
        }
    }

    protected void testPaymentPreAuthorization(IGPConnector connector) {
        Payment payment = null;
        try {
            BasePayment createpayment = createTestBasePayment();
            createpayment.setPreAuthorization(true);
            payment = connector.getAppToken(CLIENT_ID, CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            handleException(e);
        }
        Assert.assertNotNull(payment);
        Assert.assertTrue(payment.getId() > 0);
    }

    protected long testPaymentRecurrency(IGPConnector connector) {
        Payment payment = null;
        try {

            BasePayment createpayment = createTestBasePayment();
            Recurrence recurrence = new Recurrence();
            recurrence.setRecurrencePeriod(1);
            recurrence.setRecurrenceState(Recurrence.RecurrenceState.STARTED);
            recurrence.setRecurrenceCycle(RecurrenceCycle.WEEK);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 2016);
            calendar.set(Calendar.MONTH, 2);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            recurrence.setRecurrenceDateTo(calendar.getTime());
            createpayment.setRecurrence(recurrence);
            payment = connector.getAppToken(CLIENT_ID, CLIENT_SECRET).createPayment(createpayment);
        } catch (GPClientException e) {
            handleException(e);
        }
        Assert.assertNotNull(payment);
        Assert.assertTrue(payment.getId() > 0);
        return payment.getId();
    }

    protected void testPaymentVoidAuthorization(IGPConnector connector) {
        long id = testPaymentRecurrency(connector);
        try {
            PaymentResult voidAuthorization = connector
                    .getAppToken(CLIENT_ID, CLIENT_SECRET, OAuth.SCOPE_PAYMENT_ALL).
                            voidAuthorization(id);

            Assert.assertNotNull(voidAuthorization);
            Assert.assertTrue(voidAuthorization.getId() > 0);
        } catch (GPClientException ex) {
            handleException(ex);
        }
    }

    public static void handleException(GPClientException e) {
        List<ErrorElement> errorMessages = e.getErrorMessages();
        StringBuilder builder = new StringBuilder();
        builder.append("E: ");
        for (ErrorElement element : errorMessages) {
            builder.append(element.getErrorName()).append(", ");
        }
        logger.log(Level.SEVERE,builder.toString());
        Assert.fail(builder.toString());
    }
}

