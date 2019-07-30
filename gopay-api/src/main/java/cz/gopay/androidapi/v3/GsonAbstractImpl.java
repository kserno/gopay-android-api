package cz.gopay.androidapi.v3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;


import cz.gopay.androidapi.v3.exception.WebClientException;
import cz.gopay.androidapi.v3.model.APIError;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by fs on 23.2.16.
 */
public class GsonAbstractImpl {

    protected static final String AUTHORIZATION = "Authorization";
    protected static final String SCOPE = "scope";
    protected static final String GRANT_TYPE = "grant_type";
    protected static final String CONTENT_TYPE = "Content-Type";
    protected static final String ACCEPT = "Accept";

    public static final String APPLICATION_JSON = "application/json";
    public static final String APPLICATION_URLENCODED = "application/x-www-form-urlencoded";

    protected OkHttpClient okHttpClient;

    protected String apiUrl;
    protected Logger logger;
    private Gson gson;

    public GsonAbstractImpl(String apiUrl) {
        this.apiUrl = apiUrl;
        logger = Logger.getLogger("GP-API");
        gson = new GsonBuilder().registerTypeAdapter(Date.class,new GPDateAdapter()).create();
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }


    protected <T> T unMarshall(Response response, Class<T> entity)  {
        try {


            int code = response.code();


            if (response.body() == null) {
                throw new IllegalArgumentException("response body is null");
            }
            String json = new String(response.body().bytes());

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
