# Gopay Android API


Detailed guide: [https://doc.gopay.com](https://doc.gopay.com)


# Requirements

 - Android studio/Gradle 

# Building

```bash
git clone https://github.com/gopaycommunity/gopay-android-api.git
cd gopay-android-api
gradle clean build
```

# Installation #

Artifacts are located in the maven jcenter repository. 


```bash
//model classes, connector
compile 'cz.gopay:androidapi:1.0.0'

//webview
compile 'cz.gopay:androidui:1.0.0'

```
## OAuth
 
 
 To be able to communicate with our gateway it's required to create an auth token.
 
 ```Java
 IGPConnector connector = GPAndroidConnector.build(<API_URL>);
 connector.getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>); 
 ```
 
 The token gets cached in GPConnector object and its lifetime is 30 minutes. The method ` getAppToken(String, String)` creates token in a scope `"payment-create"`. If you would like to create a token in a different scope call method `getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>,<SCOPE>)` Once the token expires its required to obtain a new one by calling the method getAppToken again.
     

# Basic usage 

## Creating an instance of GPConnector

```Java
IGPConnector connector = GPAndroidConnector.build(<API_URL>);
```
 
The connector provides methods for interacting with our gateway.
 
## Avalaible methods 
  
| Method        | API Action    |
| :------------ |:--------------|
| [createPayment](#create) | https://doc.gopay.com/en/#standard-payment |
| [paymentStatus](#status) | https://doc.gopay.com/en/#status-of-the-payment |
| [refundPayment](#refund) | https://doc.gopay.com/en/#refund-of-the-payment-(cancelation) |
| [createRecurrentPayment](#createrec) | https://doc.gopay.com/en/#recurring-payment |
| [voidRecurrency](#voidrec) | https://doc.gopay.com/en/#cancellation-of-the-recurring-payment |
| [voidAuthorization](#voidauth) | https://doc.gopay.com/en/#cancellation-of-the-pre-authorized-payment |
| [capturePayment](#capt) | https://doc.gopay.com/en/#charge-of-pre-authorized-payment |
 
 
###### Create a payment <a id="create">

```Java
BasePayment payment = PaymentFactory.createBasePaymentBuilder()
    .order(<ORDER_NUMBER>, <AMOUNT>, Currency.EUR, <DESCRIPTION>)
    .addItem(<ITEM_NAME>, <AMOUNT>, <FEE>, <COUNT>)
    .addAdditionalParameter(<Key>, <VALUE>)
    .withCallback(<RETURN_URL>, <NOTIFY_URL>)
    .payer(<Payer>)
    .inLang(Lang.EN)
    .toEshop(<GO_ID>)
    .build();
try {
    Payment result = connector.createPayment(payment);
} catch (GPClientException e) {
    
}
 ```

###### Payment status <a id="status">

```Java
try {
    Payment payment = connector.paymentStatus(<PAYMENT_ID>);
} catch (GPClientException e) {
     //
}
```
 
###### Payment refund <a id="refund">

```Java
try {
      PaymentResult result = connector.refundPayment(<PAYMENT_ID>, <AMOUNT>);
} catch (GPClientException e) {
      //
}
```
 
###### Create preauthorized payment 

```Java
Payment payment = PaymentFactory.createPaymentBuilder().preauthorize()...
try {
    connector.createPayment(payment);
} catch (GPClientException ex) {
    //
}
```

###### Void authorization <a id="voidauth">

```Java
try {
    PaymentResult voidAuthorization = connector.voidAuthorization(<ID>);
} catch (GPClientException ex) {
    //
}
```

###### Recurrent payment <a id="createrec">

```Java
Calendar calendar = Calendar.getInstance();
calendar.set(Calendar.YEAR, 2016);
calendar.set(Calendar.MONTH, 2);
calendar.set(Calendar.DAY_OF_MONTH, 1);
Recurrence r = Recurrence.build(calendar.getTime())
    .withTimeInterval(RecurrenceCycle.WEEK, 1)
    .inState(Recurrence.RecurrenceState.STARTED);
payment.setRecurrence(r);
 
try {
    connector.createPayment(payment);
} catch {GPClientException e) {
    //
}
```

###### Capture payment <a id="capt">

```Java
try {
    PaymentResult capture = connector.capturePayment(<ID>);
} catch (GPClientException ex) {
    //
}
```

###### Void recurrency <a id="voidrec">

 ```Java
try {
    PaymentResult voidRecurrency = connector.voidRecurrency(<ID>);
} catch (GPClientException ex) {
     //
}
```



All methods above throw checked exception GPClientException on a failure.

```Java
try {
    HttpClientGPConnector.build(<API_URL>).getAppToken(<CLIENT_ID>,<CLIENT_CREDENTIALS>)
        .createPayment(payment);
} catch (GPClientException e) {
    for (ErrorElement err : e.getError().getErrorMessages()) {
        int code = err.getErrorCode();
        String message = err.getMessage();
        String field = err.getField();
        
    }
}
```
 
 
 For more code samples check out source code of module (gopay-example)[https://github.com/gopaycommunity/gopay-android-api/tree/master/gopay-example/src/main/java/cz/gopay/android/sample]
 
 

    
####  Gopay UI 

UI module uses webview, which you may initialize once you retrieve gateway url.

    Intent intent = new Intent(context, GPWebViewActivity.class);
    /* gateway url */
    intent.putExtra("gw-url", gw_url);
    /* Back button text. */
    intent.putExtra("back-btn-name", "Back");

 
- ### Model builders
  
 You can use several builder objects to achieve better code readability.
  
  - Payment builder

    ```Java
    BasePayment payment = PaymentFactory.createBasePaymentBuilder()
            .order(<ORDER_NUMBER>, <AMOUNT>, Currency.EUR, <DESCRIPTION>)
            .addItem(<ITEM_NAME>, <AMOUNT>, <FEE>, <COUNT>)
            .addAdditionalParameter(<Key>, <VALUE>)
            .withCallback(<RETURN_URL>, <NOTIFY_URL>)
            .payer(<Payer>)
            .inLang(Lang.EN)
            .toEshop(<GO_ID>)
            .build();
    ```

 - Payer builder
  
    ```Java
    Payer payer = new PayerBuilder()
            .withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
            .addAllowedSwift(<SWIFT>).build();
      ```         

## Contributing

Contributions from others would be very much appreciated! Send pull request/ issue. Thanks!

## License

Copyright (c) 2016 GoPay.com. MIT Licensed, see LICENSE for details.
