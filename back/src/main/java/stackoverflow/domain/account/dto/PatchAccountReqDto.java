package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;
import stackoverflow.domain.account.entity.Account;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.Optional;

@Getter
@Setter
public class PatchAccountReqDto {
    @Pattern(regexp = "^[a-zA-Z가-헿0-9]{4,}$",
            message = "4자 이상부터 가능하며 특수 문자가 없어야 합니다.")
    private String nickname;

    @Pattern(regexp = "(?=.*\\d)(?=.*[a-zA-ZS]).{8,}",
            message = "영어와 숫자를 최소 1개 포함하여 8자 이상이어야합니다.")
    private String password;

    private MultipartFile profile;


}