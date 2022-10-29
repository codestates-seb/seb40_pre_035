package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PatchAccountReqDto {
    private String nickname;
    private String profile;
    private String password;
}