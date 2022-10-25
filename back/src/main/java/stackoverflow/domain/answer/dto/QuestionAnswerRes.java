package stackoverflow.domain.answer.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.dto.QuestionAccountRes;
import stackoverflow.global.auditing.BaseTime;

@Getter
@Setter
public class QuestionAnswerRes extends BaseTime {

    private Long id;

    private String title;

    private String content;

    private QuestionAccountRes account;
}
