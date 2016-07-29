package cz.gopay.api.v3.model.payment;


import java.util.List;

import cz.gopay.api.v3.model.common.Currency;
import cz.gopay.api.v3.model.payment.support.AdditionalParam;
import cz.gopay.api.v3.model.payment.support.OrderItem;

/**
 *
 * @author Zbynek Novak novak.zbynek@gmail.com
 *
 */
public class NextPayment {

    private Long amount;

    private Currency currency;

    private String order_number;

    private String order_description;

    private List<OrderItem> items;

    private List<AdditionalParam> additional_params;

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

    public String getOrderNumber() {
        return order_number;
    }

    public void setOrderNumber(String orderNumber) {
        this.order_number = orderNumber;
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

    public List<AdditionalParam> getAdditionalParams() {
        return additional_params;
    }

    public void setAdditionalParams(List<AdditionalParam> additionalParams) {
        this.additional_params = additionalParams;
    }

    @Override
    public String toString() {
        return String.format("CreateNextPayment [amount=%s, currency=%s, orderNumber=%s, orderDescription=%s]",
                amount, currency, order_number,
                order_description);
    }

    public static NextPayment create() {
        NextPayment result = new NextPayment();
        return result;
    }
    
}
