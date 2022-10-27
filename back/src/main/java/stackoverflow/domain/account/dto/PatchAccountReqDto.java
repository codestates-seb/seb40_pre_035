package stackoverflow.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PatchAccountReqDto {
    private String nickname;
    private String profileImg;
}