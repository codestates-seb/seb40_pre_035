package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.global.auditing.BaseTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class AnswerResDto extends BaseTime {
    private Long id;

    private String content;

    private int totalVote;
    private AnswerAccountResDto account;

    private Long questionId;

    public void AnswerAccountResDto(Answer answer) {
        this.account.setId(answer.getId());
    }

    public AnswerResDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.account = new AnswerAccountResDto(
                answer.getAccount().getId(),
                        answer.getAccount().getEmail(),
                        answer.getAccount().getProfile(),
                        answer.getAccount().getNickname()
        );
        this.questionId = answer.getQuestion().getId();  // 이 부분 추가해야함
        setCreatedAt(answer.getCreatedAt());
        setModifiedAt(answer.getModifiedAt());
    }
}
