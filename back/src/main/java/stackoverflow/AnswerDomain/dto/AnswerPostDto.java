package stackoverflow.AnswerDomain.dto;

import lombok.Data;
import stackoverflow.AnswerDomain.entity.Answer;

@Data
public class AnswerPostDto {
    private Long answerId;
    private Long accountId;
    private Long questionId;
    private String title;
    private String content;

    public Answer setAnswer() {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setAccountId(accountId);
        answer.setQuestionId(questionId);
        answer.setTitle(title);
        answer.setContent(content);

        return answer;
    }
}
