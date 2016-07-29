package cz.gopay.api.v3.android;

import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.entity.ContentType;

import java.io.IOException;

import cz.gopay.api.v3.AuthClient;
import cz.gopay.api.v3.GsonAbstractImpl;
import cz.gopay.api.v3.exception.WebClientException;
import cz.gopay.api.v3.model.access.AccessToken;
import cz.gopay.api.v3.model.access.AuthHeader;

/**
 * Created by Frantisek Sichinger on 23.2.16.
 */
public class AndroidAuthClient extends GsonAbstractImpl implements AuthClient {


    protected AndroidAuthClient(String apiUrl) {
        super(apiUrl);
    }


    @Override
    public AccessToken loginApplication(AuthHeader authHeader, String grantType, String scope) {
        Form form = Form.form();
        form.add(SCOPE, scope);
        form.add(GRANT_TYPE, grantType);
        Response respose = null;

        try {
            respose = Request.Post(apiUrl + "/oauth2/token").
                    addHeader(AUTHORIZATION, authHeader.getAuhorization())
                    .addHeader(CONTENT_TYPE, "application/x-www-form-urlencoded").
                            bodyForm(form.build())
                    .bodyString("grant_type=client_credentials&scope=" + scope, ContentType.APPLICATION_JSON)
                    .execute();
        } catch (IOException ex) {
            throw new WebClientException(ex);
        }

        return unMarshall(respose, AccessToken.class);
    }
}
