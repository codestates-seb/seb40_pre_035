package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@Setter
public class PatchAccountReqDto {
    @Pattern(regexp = "^[a-zA-Z가-헿0-9]{1,4}$",
            message = "4자 미만에 특수 문자가 없어야 합니다.")
    private String nickname;

    private String profile;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-ZS]).{8,}",
            message = "영어와 숫자를 최소 1개 포함하여 8자 이상이어야합니다.")
    private String password;

    public Account toAccount() {
        Account account = new Account();
        account.setPassword(this.password);
        account.setNickname(this.nickname);
        account.setProfile(this.profile);

        return account;
    }
}