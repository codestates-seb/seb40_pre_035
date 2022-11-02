package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;

@Getter
@Setter
@NoArgsConstructor
public class LoginAccountResDto {
    private String email;
    private String nickname;
    private String profile;

    public LoginAccountResDto(Account account) {
        this.email = account.getEmail();
        this.nickname = account.getNickname();
        this.profile = account.getProfile();
    }
}
