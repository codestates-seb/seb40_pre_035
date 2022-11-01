package stackoverflow.domain.answer.dto;

import lombok.*;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.question.entity.Question;


@Getter
@Setter
public class AnswerReqDto {

    private String content;

    private int totalVote;

    private Long accountId;

    private Long questionId;

    public Answer toAnswer () {
        Answer answer = new Answer();
        answer.setContent(this.content);
        answer.setSelected(false);
        Account account = new Account();
        account.setId(this.accountId);
        answer.setAccount(account);
        Question question = new Question(this.questionId);
        answer.setQuestion(question);

        return answer;
    }

}
