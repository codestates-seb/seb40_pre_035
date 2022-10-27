package stackoverflow.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostAccountReqDto {
    private String email;
    private String password;
    private String nickname;
}