package cz.gopay.android.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.Arrays;

import cz.gopay.androidapi.v3.model.common.Currency;
import cz.gopay.androidapi.v3.model.payment.BasePayment;
import cz.gopay.androidapi.v3.model.payment.BasePaymentBuilder;
import cz.gopay.androidapi.v3.model.payment.Lang;
import cz.gopay.androidapi.v3.model.payment.PaymentFactory;
import cz.gopay.androidapi.v3.model.payment.support.Callback;
import cz.gopay.androidapi.v3.model.payment.support.Payer;
import cz.gopay.androidapi.v3.model.payment.support.PayerBuilder;
import cz.gopay.androidapi.v3.model.payment.support.PaymentInstrument;


public class SampleActivity extends AppCompatActivity implements GPActivity {

    private Button button;

    private ProgressBar progressBar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        button = (Button) findViewById(R.id.buttonUrl);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                /* Test credentials */
                final String API_URL = "https://gw.sandbox.gopay.com/api";
                final String CLIENT_ID = "1744960415";
                final String CLIENT_SECRET = "h9wyRz2s";
                final Long GOID = 8339303643L;

                String url = "https://www.eshop123456.cz/";

                /* Create payment object */
                Payer payer = new PayerBuilder().withAllowedPaymentInstruments(Arrays.asList(PaymentInstrument.BANK_ACCOUNT))
                        .addAllowedSwift("FIOBCZPP").withDefaultPaymentInstrument(PaymentInstrument.BANK_ACCOUNT).withPaymentInstrument(PaymentInstrument.BANK_ACCOUNT).build();
                BasePaymentBuilder builder = PaymentFactory.createBasePaymentBuilder();
                final BasePayment payment = builder.withCallback(Callback.of(url + "success", url + "notify"))
                        .order("123", 10L, Currency.EUR, "description")
                        .inLang(Lang.EN)
                        .addAdditionalParameter("AKey2", "AValue")
                        .addItem("An item", 1L,1L,1L)
                        .toEshop(GOID)
                        .payer(payer).build();

                progressBar.setVisibility(View.VISIBLE);
                new APITask(payment, SampleActivity.this).execute(API_URL, CLIENT_ID, CLIENT_SECRET);
            }
        });
    }

    @Override
    public ProgressBar getProgressBar() {
        return progressBar;
    }

}

