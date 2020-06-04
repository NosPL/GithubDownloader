
package GithubClient.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Committer {

    @JsonProperty("avatar_url")
    private String avatarUrl;
    private String date;
    private String email;
    @JsonProperty("events_url")
    private String eventsUrl;
    @JsonProperty("followers_url")
    private String followersUrl;
    @JsonProperty("following_url")
    private String followingUrl;
    @JsonProperty("gists_url")
    private String gistsUrl;
    @JsonProperty("gravatar_id")
    private String gravatarId;
    @JsonProperty("html_url")
    private String htmlUrl;
    private long id;
    private String login;
    private String name;
    @JsonProperty("node_id")
    private String nodeId;
    @JsonProperty("organizations_url")
    private String organizationsUrl;
    @JsonProperty("received_events_url")
    private String receivedEventsUrl;
    @JsonProperty("repos_url")
    private String reposUrl;
    @JsonProperty("site_admin")
    private Boolean siteAdmin;
    @JsonProperty("starred_url")
    private String starredUrl;
    @JsonProperty("subscriptions_url")
    private String subscriptionsUrl;
    private String type;
    private String url;

}
