package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;

@Getter
@Setter
public class PostAccountReqDto {
    private String email;
    private String password;
    private String nickname;

    public Account toAccount() {
        Account account = new Account();
        account.setEmail(this.email);
        account.setPassword(this.password);
        account.setNickname(this.nickname);

        return account;
    }
}