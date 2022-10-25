package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.global.auditing.BaseTime;

@Getter
@Setter
public class QuestionAccountRes extends BaseTime {

    private Long id;

    private String email;

    private String path;

    private String nickname;
}
