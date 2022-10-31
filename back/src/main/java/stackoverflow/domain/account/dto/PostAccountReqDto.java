package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class PostAccountReqDto {
    @Email
    private String email;
    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-ZS]).{8,}",
            message = "영어와 숫자를 최소 1개 포함하여 8자 이상이어야합니다.")
    private String password;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-헿0-9]{1,4}$",
            message = "4자 미만에 특수 문자가 없어야 합니다.")
    private String nickname;

    public Account toAccount() {
        Account account = new Account();
        account.setEmail(this.email);
        account.setPassword(this.password);
        account.setNickname(this.nickname);

        return account;
    }
}