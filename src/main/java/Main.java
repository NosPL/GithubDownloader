import GithubClient.GithubClient;

import java.io.File;
import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        File file = new File("some directory");
        LocalDate since = LocalDate.of(2018, 9, 10);
        LocalDate until = LocalDate.of(2018, 9, 11);

        GithubClient githubClient = new GithubClient();
        githubClient.downloadCommits()
                .fromOwner("owner")
                .fromRepo("sada")
                .toFile(file)
                .madeBy("committer")
                .base64Credentials("encodedUser:encodedPassword")
                .since(since)
                .until(until)
                .zip()
                .execute();
    }
}
