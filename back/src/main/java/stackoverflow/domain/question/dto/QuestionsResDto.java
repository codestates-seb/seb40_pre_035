package stackoverflow.domain.question.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.dto.QuestionAccountResDto;
import stackoverflow.global.auditing.BaseTime;

@Getter
@Setter
public class QuestionsResDto extends BaseTime {

    private Long id;

    private String title;

    private String content;

    private int totalVote;

    private int answerCount;

    private boolean selectedAnswer;

    private QuestionAccountResDto account;
}
