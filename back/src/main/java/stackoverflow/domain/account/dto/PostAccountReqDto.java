package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import stackoverflow.domain.account.entity.Account;

import javax.validation.constraints.*;

@Getter
@Setter
public class PostAccountReqDto {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$",
            message = "이메일 형식에 맞지 않습니다.")
    private String email;
    @NotBlank
    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-ZS]).{8,}",
            message = "영어와 숫자를 최소 1개 포함하여 8자 이상이어야합니다.")
    private String password;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z가-헿0-9]{4,}$",
            message = "4자 이상부터 가능하며 특수 문자가 없어야 합니다.")
    private String nickname;

    @NotNull
    private MultipartFile profile;

    public Account toAccount(String profilePath) {
        Account account = new Account();
        account.setEmail(this.email);
        account.setPassword(this.password);
        account.setNickname(this.nickname);
        account.setProfile(profilePath);

        return account;
    }
}