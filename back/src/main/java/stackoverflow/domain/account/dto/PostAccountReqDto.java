package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostAccountReqDto {
    private String email;
    private String password;
    private String nickname;
}