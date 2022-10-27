package stackoverflow.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AccountResDto {
    private String email;
    private String nickname;
    private String profileImg;
}