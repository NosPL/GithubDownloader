package GithubClient;

import java.io.File;
import java.net.URL;

public interface FileDownloader {
    void downloadFile(URL url, File file);
}
