package cz.gopay.androidapi.v3.model.payment;

import java.util.ArrayList;
import java.util.List;


import cz.gopay.androidapi.v3.model.common.Currency;
import cz.gopay.androidapi.v3.model.payment.support.AdditionalParam;
import cz.gopay.androidapi.v3.model.payment.support.Payer;
import cz.gopay.androidapi.v3.model.payment.support.PaymentInstrument;
import cz.gopay.androidapi.v3.model.payment.support.Preauthorization;
import cz.gopay.androidapi.v3.model.payment.support.Recurrence;
import cz.gopay.androidapi.v3.model.payment.support.Target;

public class Payment {

    public enum SessionState {
        CREATED,
        PAYMENT_METHOD_CHOSEN,
        PAID,
        AUTHORIZED,
        CANCELED,
        TIMEOUTED,
        REFUNDED,
        PARTIALLY_REFUNDED;
    }

    private Long id;

    private Long parent_id;

    private String order_number;

    private SessionState state;

    private PaymentInstrument payment_instrument;

    private Long amount;

    private Currency currency;

    private Payer payer;

    private Target target;

    private Recurrence recurrence;

    private Preauthorization preauthorization;

    private List<AdditionalParam> additional_params;

    private String lang;

    private String gw_url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionState getState() {
        return state;
    }

    public void setState(SessionState state) {
        this.state = state;
    }

    public PaymentInstrument getPaymentInstrument() {
        return payment_instrument;
    }

    public void setPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.payment_instrument = paymentInstrument;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public Recurrence getRecurrence() {
        return recurrence;
    }

    public void setRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
    }

    public List<AdditionalParam> getAdditionalParams() {
        return additional_params;
    }

    public void setAdditionalParams(List<AdditionalParam> aditionalParams) {
        this.additional_params = aditionalParams;
    }

    public String getOrderNumber() {
        return order_number;
    }

    public void setOrderNumber(String orderNumber) {
        this.order_number = orderNumber;
    }

    public Preauthorization getPreAuthorization() {
        return preauthorization;
    }

    public void setPreAuthorization(Preauthorization preAuthorization) {
        this.preauthorization = preAuthorization;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getGwUrl() {
        return gw_url;
    }

    public void setGwUrl(String gwUrl) {
        this.gw_url = gwUrl;
    }

    public Long getParentId() {
        return parent_id;
    }

    public void setParentId(Long parentId) {
        this.parent_id = parentId;
    }

    @Override
    public String toString() {
        return String.format(
                "Payment [id=%s, parentId=%s, state=%s, paymentInstrument=%s, amount=%s, currency=%s, payer=%s, target=%s, recurrence=%s, aditionalParams=%s, preAuthorization=%s, lang=%s]",
                id, parent_id, state, payment_instrument, amount, currency, payer, target, recurrence, additional_params,
                preauthorization, lang);
    }

    public static Payment create(Long id, Long parentId, SessionState state) {
        Payment result = new Payment();
        result.setId(id);
        result.setParentId(parentId);
        result.setState(state);
        return result;
    }

    public Payment chosenPaymentInstrument(PaymentInstrument paymentInstrument) {
        this.payment_instrument = paymentInstrument;
        return this;
    }

    public Payment withPrice(Long amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
        return this;
    }

    public Payment identifiedBy(String orderNumber) {
        this.order_number = orderNumber;
        return this;
    }

    public Payment withPayerParty(Payer payer) {
        this.payer = payer;
        return this;
    }

    public Payment withTargetParty(Target target) {
        this.target = target;
        return this;
    }

    public Payment withRecurrence(Recurrence recurrence) {
        this.recurrence = recurrence;
        return this;
    }

    public Payment asChildOf(Long parentId) {
        this.parent_id = parentId;
        return this;
    }

    public Payment addAdditionalParam(String name, String value) {
        if (name == null || value == null) {
            return this;
        }
        if (additional_params == null) {
            this.additional_params = new ArrayList<>();
        }
        this.additional_params.add(AdditionalParam.of(name, value));
        return this;
    }

    public Payment addAdditionalParam(AdditionalParam param) {
        if (param == null) {
            return this;
        }
        if (additional_params == null) {
            this.additional_params = new ArrayList<>();
        }
        this.additional_params.add(param);
        return this;
    }

    public Payment withPreAuthorization(Preauthorization preAuthorization) {
        this.preauthorization = preAuthorization;
        return this;
    }

    public Payment onURL(String url) {
        this.gw_url = url;
        return this;
    }

    public Payment inLang(String lang) {
        this.lang = lang;
        return this;
    }
}
