package cz.gopay.api.v3;



import cz.gopay.api.v3.model.access.AuthHeader;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.NextPayment;
import cz.gopay.api.v3.model.payment.Payment;
import cz.gopay.api.v3.model.payment.PaymentResult;



/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 */
public interface PaymentClient {

    public Payment createPayment(AuthHeader authHeader, BasePayment createPayment);

    public PaymentResult refundPayment(AuthHeader authHeader, Long id, Long amount);

    public Payment createRecurrentPayment(AuthHeader authHeader, Long id, NextPayment createPayment);

    public PaymentResult voidRecurrence(AuthHeader authHeader, Long id);

    public PaymentResult capturePayment(AuthHeader authHeader, Long id);

    public PaymentResult voidAuthorization(AuthHeader authHeader, Long id);

    public Payment getPayment(AuthHeader authHeader, Long id);

}
