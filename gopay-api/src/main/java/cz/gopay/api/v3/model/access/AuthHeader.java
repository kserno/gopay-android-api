package cz.gopay.api.v3.model.access;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class AuthHeader {

    private String auhorization;

    public String getAuhorization() {
        return auhorization;
    }

    public void setAuhorization(String auhorization) {
        this.auhorization = auhorization;
    }

    @Override
    public String toString() {
        return "AuthHeader [authorization=" + auhorization + "]";
    }

    public static AuthHeader build(String clientId, String clientSecret) {
        try {
            AuthHeader result = new AuthHeader();

            String toEncode = clientId + ":" + clientSecret;
            String ulrEncoded = URLEncoder.encode(toEncode, "UTF-8");
            String base64 = "Basic " + new String(Base64.encode(ulrEncoded.getBytes(), Base64.NO_WRAP));

            result.setAuhorization(base64);

            return result;

        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    public static AuthHeader build(String accessToken) {
        AuthHeader result = new AuthHeader();
        result.setAuhorization("Bearer " + accessToken);

        return result;
    }

}
