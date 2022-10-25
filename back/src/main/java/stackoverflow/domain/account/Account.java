package stackoverflow.domain.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
//@Entity
public class Account extends BaseTime {
    @Id
    private long accountId;

    private String email;

    private String password;

    private String path;

    private String nickname;

    private String roles;
}
