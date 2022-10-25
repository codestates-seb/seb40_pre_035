package stackoverflow.domain.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.global.audit.Auditable;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
//@Entity
public class Account extends Auditable {
    @Id
    private long accountId;

    private String email;

    private String password;

    private String path;

    private String nickName;

    private String roles;
}
