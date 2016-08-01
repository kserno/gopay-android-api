package cz.gopay.androidapi.v3;


import cz.gopay.androidapi.v3.model.access.AccessToken;
import cz.gopay.androidapi.v3.model.access.AuthHeader;

/**
 * @author Zbynek Novak novak.zbynek@gmail.com
 */
public interface AuthClient {

    AccessToken loginApplication(AuthHeader authHeader, String grantType, String scope);

}
