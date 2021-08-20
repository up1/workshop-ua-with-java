package domain;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class GatewayService {

    public boolean callByDomain(String domainName) {
        HttpUrl url = HttpUrl.parse("http://" + domainName);
        return url != null;
    }

    public boolean callByDomain02(String domainName) {
        HttpUrl url = HttpUrl.parse("http://" + domainName);
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = httpClient.newCall(request).execute();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
