package cz.gopay.androidapi.v3.model.access;


public class AccessToken {

    private String token_type;

    private String access_token;

    private String refresh_token;

    private long expires_in;

    public AccessToken() {
    }

    public AccessToken(String tokenType, String accessToken, String refreshToken,
            long expiresIn) {
        this.token_type = tokenType;
        this.access_token = accessToken;
        this.refresh_token = refreshToken;
        this.expires_in = expiresIn;
    }

    public String getTokenType() {
        return token_type;
    }

    public void setTokenType(String tokenType) {
        this.token_type = tokenType;
    }

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String access_token) {
        this.access_token = access_token;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getExpiresIn() {
        return expires_in;
    }

}
