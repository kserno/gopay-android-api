package cz.gopay.android.sample;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import cz.gopay.android.ui.GPWebViewActivity;
import cz.gopay.api.v3.android.GPAndroidConnector;
import cz.gopay.api.v3.exception.GPClientException;
import cz.gopay.api.v3.model.payment.BasePayment;
import cz.gopay.api.v3.model.payment.Payment;

/**
 * Created by Frantisek Sichinger on 22.3.16.
 */
public class APITask extends AsyncTask<String,Void,Payment> {

    private BasePayment payment;
    private GPActivity activity;

    public APITask(BasePayment payment,GPActivity activity) {
        this.payment = payment;
        this.activity = activity;
    }

    @Override
    protected Payment doInBackground(String... params) {
        String API_URL = params[0];
        String CLIENT_ID = params[1];
        String CLIENT_SECRET = params[2];
        Payment result = null;
        try {
            result = GPAndroidConnector.build(API_URL).getAppToken(CLIENT_ID, CLIENT_SECRET).createPayment(payment);
        } catch (GPClientException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Payment result) {
        String gwURL = result.getGwUrl();
        Context context = (Context) activity;
        Intent intent = new Intent(context, GPWebViewActivity.class);
        /* gateway url */
        intent.putExtra("gw-url", gwURL);
        /* Text on the back button */
        intent.putExtra("back-btn-name", "Back");
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.getProgressBar().setVisibility(View.INVISIBLE);
        activity.startActivity(intent);
    }
}
