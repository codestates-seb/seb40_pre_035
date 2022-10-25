package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionAccountRes {

    private Long id;

    private String email;

    private String profile;

    private String nickname;
}
