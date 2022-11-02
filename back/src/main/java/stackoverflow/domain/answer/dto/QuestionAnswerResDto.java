package stackoverflow.domain.answer.dto;

import lombok.Data;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.answer.entity.AnswerVote;
import stackoverflow.global.auditing.BaseTime;
import stackoverflow.global.common.enums.VoteState;

import java.util.List;

@Data
public class QuestionAnswerResDto extends BaseTime {

    private Long id;

    private String content;

    private long totalVote;

    private boolean selected;

    private AnswerAccountResDto account;

    private Long questionId;

    public QuestionAnswerResDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.totalVote = getTotalVote(answer.getAnswerVotes());
        this.account = new AnswerAccountResDto(answer.getAccount());
        this.questionId = answer.getQuestion().getId();
        this.selected = answer.isSelected();
        setCreatedAt(answer.getCreatedAt());
        setModifiedAt(answer.getModifiedAt());
    }

    private long getTotalVote(List<AnswerVote> answerVotes) {
        int voteSize = answerVotes.size();
        long voteUpCount = answerVotes.stream()
                .filter(questionVote -> questionVote.getState().equals(VoteState.UP))
                .count();
        return 2 * voteUpCount - voteSize;
    }
}
