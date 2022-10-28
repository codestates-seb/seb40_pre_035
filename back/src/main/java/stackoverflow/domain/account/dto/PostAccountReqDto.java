package stackoverflow.domain.account.dto;

import lombok.Getter;

@Getter
public class PostAccountReqDto {
    private String email;
    private String password;
    private String nickname;
}