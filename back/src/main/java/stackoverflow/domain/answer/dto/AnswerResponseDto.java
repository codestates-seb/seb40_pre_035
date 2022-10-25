package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import stackoverflow.domain.answer.entity.Answer;

@Data
@AllArgsConstructor
public class AnswerResponseDto {
    private Long answerId;
    private Long accountId;
    private Long questionId;

    private String title;
    private String content;

    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.accountId = answer.getAccountId();
        this.questionId = answer.getQuestionId();
        this.title = answer.getTitle();
        this.content = answer.getContent();
    }
}
