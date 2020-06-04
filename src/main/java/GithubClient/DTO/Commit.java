
package GithubClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Commit {

    private Author author;
    @JsonProperty("comment_count")
    private long commentCount;
    private Committer committer;
    private String message;
    private Tree tree;
    private String url;
    private Verification verification;

}
