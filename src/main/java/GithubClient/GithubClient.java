package GithubClient;

import GithubClient.DTO.FileDto;
import GithubClient.DTO.FullCommitInfo;
import GithubClient.DTO.ShortCommitInfo;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.http.client.utils.URIBuilder;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class GithubClient {

    private WebApiClient webApiClient = new WebApiClientImpl();
    private FileDownloader fileDownloader = new FileDownloaderImpl();
    private FileZipper fileZipper = new FileZipper();
    private DownloadBuilder builder;


    public DownloadBuilder downloadCommits() {
        return new DownloadBuilder();
    }

    @SneakyThrows
    private void downloadCommits(DownloadBuilder downloadBuilder) {
        this.builder = downloadBuilder;
        ShortCommitInfo[] shortCommits = getShortCommits();
        List<FullCommitInfo> fullCommits = getFullCommits(shortCommits);
        fullCommits.forEach(c -> downloadCommit(c));
        if (builder.zip) {
            fileZipper.zipFile(builder.destination, builder.destination);
            FileUtils.deleteDirectory(builder.destination);
        }

    }

    private ShortCommitInfo[] getShortCommits() {
        return webApiClient.getContent(builder.uri, builder.requestHeaders, ShortCommitInfo[].class);
    }

    @SneakyThrows
    private List<FullCommitInfo> getFullCommits(ShortCommitInfo[] shortCommits) {
        List<FullCommitInfo> fullCommits = new ArrayList<>();
        for (ShortCommitInfo shortCommit : shortCommits) {
            URI uri = new URI(shortCommit.getUrl());
            FullCommitInfo fullCommit = webApiClient.getContent(uri, builder.requestHeaders, FullCommitInfo.class);
            fullCommits.add(Objects.requireNonNull(fullCommit));
        }
        return fullCommits;
    }

    @SneakyThrows
    private void downloadCommit(FullCommitInfo commit) {
        for (FileDto fileDto : commit.getFiles()) {
            if (!fileDto.getStatus().equals("removed")) {
                File commitFolder = new File(builder.destination.toString() + "\\" + commit.getSha());
                commitFolder.mkdir();
                URL url = new URL(fileDto.getRawUrl());
                File file = new File(commitFolder.toString() + "\\" + fileDto.getFilename());
                this.fileDownloader.downloadFile(url, file);
            }
        }
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public class DownloadBuilder {
        @NonNull
        private String userName;
        @NonNull
        private String repoName;
        @NonNull
        private File destination;
        private boolean zip;
        private Map<String, String> uriArguments = new HashMap<>();
        private Map<String, String> requestHeaders = new HashMap<>();
        private URI uri;

        public DownloadBuilder fromOwner(@NonNull String userName) {
            this.userName = userName;
            return this;
        }

        public DownloadBuilder fromRepo(@NonNull String repo) {
            this.repoName = repo;
            return this;
        }

        public DownloadBuilder since(@NonNull LocalDate since) {
            this.uriArguments.put("since", dateInCorrectFormat(since));
            return this;
        }

        public DownloadBuilder until(@NonNull LocalDate until) {
            this.uriArguments.put("until", dateInCorrectFormat(until));
            return this;
        }

        public DownloadBuilder madeBy(@NonNull String authorName) {
            this.uriArguments.put("madeBy", authorName);
            return this;
        }

        public DownloadBuilder toFile(@NonNull File destination) {
            this.destination = destination;
            return this;
        }

        public DownloadBuilder base64Credentials(@NonNull String loginAndPasswordEncoded) {
            this.requestHeaders.put("Authorization", "Basic " + loginAndPasswordEncoded);
            return this;
        }

        public DownloadBuilder zip() {
            this.zip = true;
            return this;
        }

        public DownloadBuilder zip(boolean zip) {
            this.zip = zip;
            return this;
        }

        public void execute() {
            this.uri = buildUri();
            downloadCommits(this);
        }

        @SneakyThrows
        private URI buildUri() {
            URIBuilder builder = new URIBuilder()
                    .setScheme("https")
                    .setHost("api.github.com");
            builder.setPath("/repos/" + userName + "/" + repoName + "/commits");
            uriArguments.forEach((k, v) -> builder.addParameter(k, v));
            return builder.build();
        }

        private String dateInCorrectFormat(LocalDate date) {
            return date.atStartOfDay().toString() + ":00Z";
        }
    }
}
