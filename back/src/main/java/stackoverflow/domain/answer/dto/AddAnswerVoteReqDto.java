package stackoverflow.domain.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.entity.AnswerVote;
import stackoverflow.global.common.enums.VoteState;

@Getter
@Setter
@NoArgsConstructor
public class AddAnswerVoteReqDto {

    private VoteState state;

    public AnswerVote toAnswerVote(Long accountId, Long answerId) {
        return AnswerVote.builder()
                .account(new Account(accountId))
                .answer(new Answer(answerId))
                .state(this.state)
                .build();
    }

}
