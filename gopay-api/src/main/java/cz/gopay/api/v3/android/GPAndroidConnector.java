package cz.gopay.api.v3.android;

import cz.gopay.api.v3.AuthClient;
import cz.gopay.api.v3.PaymentClient;
import cz.gopay.api.v3.model.access.AccessToken;


public class GPAndroidConnector extends AbstractGPConnector {


    private GPAndroidConnector(String apiUrl) {
        super(apiUrl);
    }

    public GPAndroidConnector(String apiUrl, AccessToken token) {
        super(apiUrl, token);
    }

    public static GPAndroidConnector build(String apiurl) {
        return new GPAndroidConnector(apiurl);
    }

    @Override
    protected <T> T createRESTClientProxy(String apiUrl, Class<T> proxy) {
        if (proxy == AuthClient.class) {
            return (T) new AndroidAuthClient(apiUrl);
        } else if (proxy == PaymentClient.class) {
            return (T) new AndroidPaymentClient(apiUrl);
        }
        throw new IllegalArgumentException("Unknown interface");
    }
}
