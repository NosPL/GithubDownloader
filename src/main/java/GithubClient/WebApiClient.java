package GithubClient;

import java.net.URI;
import java.util.Map;

public interface WebApiClient {
    String getContent(URI uri);

    String getContent(URI uri, Map<String, String> headers);

    <T> T getContent(URI uri, Class<T> clazz);

    <T> T getContent(URI uri, Map<String, String> headers, Class<T> clazz);
}
