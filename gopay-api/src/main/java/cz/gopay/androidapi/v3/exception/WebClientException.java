package cz.gopay.androidapi.v3.exception;

import cz.gopay.androidapi.v3.model.APIError;

/**
 * Created by fs on 4.3.16.
 */
public class WebClientException extends RuntimeException {
    private int code;
    private APIError error;


    public WebClientException(Exception ex) {
        super(ex);
    }

    public WebClientException(int code) {
        this(code,null);
    }

    public WebClientException(int code, APIError error) {
        this.code = code;
        this.error = error;
    }

    public int getCode() {
        return code;
    }

    public APIError getError() {
        return error;
    }

    public boolean containsEntity() {
        return error != null;
    }

}
