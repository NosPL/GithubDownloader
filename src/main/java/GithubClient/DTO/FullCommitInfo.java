
package GithubClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class FullCommitInfo {

    private Author author;
    @JsonProperty("comments_url")
    private String commentsUrl;
    private Commit commit;
    private Committer committer;
    private List<FileDto> files;
    @JsonProperty("html_url")
    private String htmlUrl;
    @JsonProperty("node_id")
    private String nodeId;
    private List<Parent> parents;
    private String sha;
    private Stats stats;
    private String url;

}
