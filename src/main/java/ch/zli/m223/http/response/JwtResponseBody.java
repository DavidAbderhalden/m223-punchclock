package ch.zli.m223.http.response;

public class JwtResponseBody {
    private String accessToken;

    public JwtResponseBody(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    } 
}
