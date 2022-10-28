package stackoverflow.domain.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    private String email;

    private String password;

    private String profile;

    private String nickname;

    private String role;

    public Account(Long id) {
        this.id = id;
    }
}
