package stackoverflow.domain.question.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.dto.QuestionAccountRes;
import stackoverflow.global.auditing.BaseTime;

@Getter
@Setter
public class QuestionRes extends BaseTime {

    private Long id;

    private String title;

    private String content;

    private int totalVote;

    private QuestionAccountRes account;
}
