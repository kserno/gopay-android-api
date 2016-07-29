package cz.gopay.api.v3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.DateTypeAdapter;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Response;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;


import cz.gopay.api.v3.exception.WebClientException;
import cz.gopay.api.v3.model.APIError;

/**
 * Created by fs on 23.2.16.
 */
public class GsonAbstractImpl {

    protected static final String AUTHORIZATION = "Authorization";
    protected static final String SCOPE = "scope";
    protected static final String GRANT_TYPE = "grant_type";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String ACCEPT = "Accept";

    protected String apiUrl;
    protected Logger logger;
    private Gson gson;

    public GsonAbstractImpl(String apiUrl) {
        this.apiUrl = apiUrl;
        logger = Logger.getLogger("GP-API");
        gson = new GsonBuilder().registerTypeAdapter(Date.class,new GPDateAdapter()).create();

    }


    protected <T> T unMarshall(Response response, Class<T> entity)  {
        try {

            HttpResponse httpresponse = response.returnResponse();
            int code = httpresponse.getStatusLine().getStatusCode();
            byte[] body = EntityUtils.toByteArray(httpresponse.getEntity());
            String json = new String(body);

            APIError error = gson.fromJson(json, APIError.class);

            if (error.getErrorMessages() != null) {
                throw new WebClientException(code,error);
            }
            return gson.fromJson(json, entity);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    protected String marshall(Object object) {
        return gson.toJson(object);
    }
}
