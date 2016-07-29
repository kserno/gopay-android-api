package cz.gopay.api.v3;

import org.junit.Test;

import cz.gopay.api.v3.android.GPAndroidConnector;

/**
 * Created by fs on 4.3.16.
 */
public class AndroidTests extends AbstractAndroidTest {


    @Test
    public void testResteasyPaymentCreate() {
        testConnectorCreatePayment(GPAndroidConnector.build(API_URL));
    }

    @Test
    public void testResteasyPaymentStatus() {
        testPaymentStatus(GPAndroidConnector.build(API_URL));
    }

    @Test
    public void testResteasyPaymentPreauthorization() {
        testPaymentPreAuthorization(GPAndroidConnector.build(API_URL));
    }

    @Test
    public void testResteasyPaymentRecurrency() {
        testPaymentRecurrency(GPAndroidConnector.build(API_URL));
    }

    //@Test
    public void testHttpClientVoidAuthorization() {
        testPaymentVoidAuthorization(GPAndroidConnector.build(API_URL));
    }

    //@Test
    public void testPaymentRefund() {
        testPaymentRefund(GPAndroidConnector.build(API_URL));
    }

}

