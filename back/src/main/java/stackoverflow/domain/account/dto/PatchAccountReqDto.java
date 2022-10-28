package stackoverflow.domain.account.dto;

import lombok.Getter;

@Getter
public class PatchAccountReqDto {
    private String nickname;
    private String profile;
    private String password;
}