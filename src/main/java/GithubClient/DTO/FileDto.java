
package GithubClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
@JsonRootName("FileDto")
public class FileDto {

    private long additions;
    @JsonProperty("blob_url")
    private String blobUrl;
    private long changes;
    @JsonProperty("contents_url")
    private String contentsUrl;
    private long deletions;
    private String filename;
    @JsonProperty("previous_filename")
    private String previousFileName;
    private String patch;
    @JsonProperty("raw_url")
    private String rawUrl;
    private String sha;
    private String status;

}
