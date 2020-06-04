package GithubClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;

class WebApiClientImpl implements WebApiClient {
    private static ObjectMapper mapper = new ObjectMapper();

    public <T> T getContent(URI uri, Class<T> clazz) {
        String content = getContent(uri);
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public <T> T getContent(URI uri, Map<String, String> headers, Class<T> clazz) {
        String content = getContent(uri, headers);
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SneakyThrows
    public String getContent(URI uri) {
        HttpResponse response = getResponse(uri);
        return getString(response);
    }

    @SneakyThrows
    public String getContent(URI uri, Map<String, String> headers) {
        HttpResponse response = geResponse(uri, headers);
        return getString(response);
    }

    private String getString(HttpResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    private HttpResponse geResponse(URI uri, Map<String, String> headers) throws IOException {
        RequestBuilder requestBuilder = RequestBuilder.get();
        requestBuilder.setUri(uri);
        headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
        HttpClient client = getHttpClient();
        HttpUriRequest request = requestBuilder.build();
        System.out.println("Executing request: " + request.toString());
        return client.execute(request);
    }

    private HttpResponse getResponse(URI uri) throws IOException {
        HttpClient client = getHttpClient();
        HttpGet request = new HttpGet(uri);
        System.out.println("Executing request: " + request.toString());
        return client.execute(request);
    }

    private HttpClient getHttpClient() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }
}

