package GithubClient;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

class FileDownloaderImpl implements FileDownloader {
    public void downloadFile(URL url, File file) {
        try {
            System.out.println("Downloading file from url: " + url.toString() + " to location: " + file.toString());
            FileUtils.copyURLToFile(url, file);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
