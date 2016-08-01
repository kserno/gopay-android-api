package cz.gopay.androidapi.v3;


import cz.gopay.androidapi.v3.exception.GPClientException;
import cz.gopay.androidapi.v3.exception.WebClientException;
import cz.gopay.androidapi.v3.model.APIError;

/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 */
public class GPExceptionHandler {

    public static void handleException(WebClientException ex) throws GPClientException {

        if (!ex.containsEntity()) {
            throw ex;
        }

        APIError error = ex.getError();

        throw new GPClientException(ex.getCode(), error);


    }

}
