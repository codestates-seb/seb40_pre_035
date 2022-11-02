package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.entity.AnswerVote;
import stackoverflow.global.auditing.BaseTime;
import stackoverflow.global.common.enums.VoteState;

import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AnswerResDto extends BaseTime {
    private Long id;

    private String content;

    private long totalVote;
    private AnswerAccountResDto account;

    private Long questionId;

    public AnswerResDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.totalVote = getTotalVote(answer.getAnswerVotes());
        this.account = new AnswerAccountResDto(
                answer.getAccount().getId(),
                        answer.getAccount().getEmail(),
                        answer.getAccount().getProfile(),
                        answer.getAccount().getNickname()
        );
        this.questionId = answer.getQuestion().getId();
        setCreatedAt(answer.getCreatedAt());
        setModifiedAt(answer.getModifiedAt());
    }

    private long getTotalVote (List<AnswerVote> answerVotes) {
        int voteSize = answerVotes.size();
        long voteUpCount = answerVotes.stream()
                .filter(answerVote -> answerVote.getState().equals(VoteState.UP))
                .count();
        return 2 * voteUpCount - voteSize;
    }
}
