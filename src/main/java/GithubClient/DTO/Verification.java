
package GithubClient.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuppressWarnings("unused")
public class Verification {

    private Object payload;
    private String reason;
    private Object signature;
    private Boolean verified;

}
