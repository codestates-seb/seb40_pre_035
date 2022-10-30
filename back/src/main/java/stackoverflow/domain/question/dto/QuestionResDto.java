package stackoverflow.domain.question.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.dto.QuestionAccountResDto;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.global.auditing.BaseTime;
import stackoverflow.global.common.enums.VoteState;

import java.util.List;

@Getter
@Setter
public class QuestionResDto extends BaseTime {

    private Long id;

    private String title;

    private String content;

    private long totalVote;

    private QuestionAccountResDto account;

    public QuestionResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.totalVote = getTotalVote(question.getQuestionVotes());
        this.account = new QuestionAccountResDto(question.getAccount());
        setCreatedAt(question.getCreatedAt());
        setModifiedAt(question.getModifiedAt());
    }

    private long getTotalVote(List<QuestionVote> questionVotes) {
        int voteSize = questionVotes.size();
        long voteUpCount = questionVotes.stream()
                .filter(questionVote -> questionVote.getState().equals(VoteState.UP))
                .count();
        return 2 * voteUpCount - voteSize;
    }
}
