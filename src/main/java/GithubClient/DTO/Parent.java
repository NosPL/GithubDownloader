
package GithubClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Parent {

    @JsonProperty("html_url")
    private String htmlUrl;
    private String sha;
    private String url;

}
