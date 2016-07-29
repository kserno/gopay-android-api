package cz.gopay.api.v3;


import cz.gopay.api.v3.exception.GPClientException;
import cz.gopay.api.v3.exception.WebClientException;
import cz.gopay.api.v3.model.APIError;

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
