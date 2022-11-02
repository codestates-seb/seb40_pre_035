package stackoverflow.domain.question.dto;

import lombok.Data;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.global.common.enums.VoteState;

@Data
public class QuestionVoteReqDto {

    private VoteState state;

    public QuestionVote toQuestionVote(Long accountId, Long questionId) {
        return QuestionVote.builder()
                .account(new Account(accountId))
                .question(Question.builder()
                        .id(questionId)
                        .build())
                .state(this.state)
                .build();
    }
}
