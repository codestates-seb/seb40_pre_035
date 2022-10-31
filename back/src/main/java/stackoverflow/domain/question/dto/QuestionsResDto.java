package stackoverflow.domain.question.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.dto.QuestionAccountResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.domain.question.entity.QuestionVote;
import stackoverflow.global.auditing.BaseTime;
import stackoverflow.global.common.enums.VoteState;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuestionsResDto extends BaseTime {

    private Long id;

    private String title;

    private String content;

    private int totalVote;

    private int answerCount;

    private boolean selectedAnswer;

    private QuestionAccountResDto account;

    public QuestionsResDto(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.totalVote = getTotalVote();
        this.answerCount = question.getAnswers().size();
        this.selectedAnswer = isSelectedAnswer();
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

    private boolean isSelectedAnswer(List<Answer> answers) {
        for (Answer answer : answers) {
            if (answer.isSelected()) {
                return true;
            }
        }
        return false;
    }
}
