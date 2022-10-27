package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerAccountResDto {

    private Long id;

    private String email;

    private String path;

    private String nickname;
}
