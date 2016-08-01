package cz.gopay.androidapi.v3.model.payment;


import java.util.List;

import cz.gopay.androidapi.v3.model.common.Currency;
import cz.gopay.androidapi.v3.model.payment.support.AdditionalParam;
import cz.gopay.androidapi.v3.model.payment.support.Callback;
import cz.gopay.androidapi.v3.model.payment.support.OrderItem;
import cz.gopay.androidapi.v3.model.payment.support.Payer;
import cz.gopay.androidapi.v3.model.payment.support.Recurrence;
import cz.gopay.androidapi.v3.model.payment.support.Target;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class BasePayment {

    private Payer payer;

    private Target target;

    private Long amount;

    private Currency currency;

    private String order_number;

    private String order_description;

    private List<OrderItem> items;

    private Callback callback;

    private Recurrence recurrence;

    private Boolean preauthorization;

    private List<AdditionalParam> additional_params;

    private String lang;

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

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public String getOder_number() {
        return order_number;
    }

    public void setOder_number(String oder_number) {
        this.order_number = oder_number;
    }

    public Payer getPayer() {
        return payer;
    }

    public void setPayer(Payer payer) {
        this.payer = payer;
    }

    public String getOrderDescription() {
        return order_description;
    }

    public void setOrderDescription(String orderDescription) {
        this.order_description = orderDescription;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public Callback getCallback() {
        return callback;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
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

    public Boolean getPreAuthorization() {
        return preauthorization;
    }

    public void setPreAuthorization(Boolean preAuthorization) {
        this.preauthorization = preAuthorization;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString() {
        return String.format(
                "BasePayment [oder_number=%s, payer=%s, target=%s, amount=%s, currency=%s, callback=%s, recurrence=%s, preAuthorization=%s, lang=%s]",
                order_number, payer, target, amount, currency, callback, recurrence, preauthorization, lang);
    }

}
