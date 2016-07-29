package cz.gopay.api.v3.model.payment.support;

import java.util.List;

/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 */
public class Payer {

    private PaymentInstrument payment_instrument;

    private List<PaymentInstrument> allowed_payment_instruments;

    private List<String> allowed_swifts;

    private PaymentInstrument default_payment_instrument;

    private String default_swift;

    private PayerContact contact;

    public PaymentInstrument getPaymentInstrument() {
        return payment_instrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.payment_instrument = paymentInstrument;
    }

    public List<PaymentInstrument> getAllowedPaymentInstruments() {
        return allowed_payment_instruments;
    }

    public List<String> getAllowedSwifts() {
        return allowed_swifts;
    }

    public void setAllowedSwifts(List<String> allowedSwifts) {
        this.allowed_swifts = allowedSwifts;
    }

    public void setAllowedPaymentInstruments(List<PaymentInstrument> allowedPaymentInstruments) {
        this.allowed_payment_instruments = allowedPaymentInstruments;
    }

    public PaymentInstrument getDefaultPaymentInstrument() {
        return default_payment_instrument;
    }

    public void setDefaultPaymentInstrument(PaymentInstrument defaultPaymentInstrument) {
        this.default_payment_instrument = defaultPaymentInstrument;
    }

    public String getDefaultSwift() {
        return default_swift;
    }

    public void setDefaultSwift(String defaultBIC) {
        this.default_swift = defaultBIC;
    }

    public PayerContact getContact() {
        return contact;
    }

    public void setContact(PayerContact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format(
                "PayerParty [paymentInstrument=%s, allowedPaymentInstruments=%s, allowedSwifts=%s, default_payment_instrument=%s, default_swift=%s, contact=%s]",
                payment_instrument, allowed_payment_instruments, allowed_swifts, default_payment_instrument, default_swift, contact);
    }

    public static Payer build() {
        return new Payer();
    }


}
