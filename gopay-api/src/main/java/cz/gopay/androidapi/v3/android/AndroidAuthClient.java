package cz.gopay.androidapi.v3.android;




import java.io.IOException;

import cz.gopay.androidapi.v3.AuthClient;
import cz.gopay.androidapi.v3.GsonAbstractImpl;
import cz.gopay.androidapi.v3.exception.WebClientException;
import cz.gopay.androidapi.v3.model.access.AccessToken;
import cz.gopay.androidapi.v3.model.access.AuthHeader;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Frantisek Sichinger on 23.2.16.
 */
public class AndroidAuthClient extends GsonAbstractImpl implements AuthClient {


    protected AndroidAuthClient(String apiUrl) {
        super(apiUrl);

    }


    @Override
    public AccessToken loginApplication(AuthHeader authHeader, String grantType, String scope) {
        Response response = null;

        RequestBody requestBody = RequestBody.create("grant_type=client_credentials&scope=" + scope,
                MediaType.parse(APPLICATION_JSON));

        try {
            Request request = new Request.Builder()
                    .url(apiUrl + "/oauth2/token")
                    .addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .addHeader(CONTENT_TYPE, "application/x-www-form-urlencoded")
                    .post(requestBody)
                    .build();

            response = okHttpClient.newCall(request).execute();


        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(response, AccessToken.class);
    }
}
