package stackoverflow.domain.answer.dto;

import lombok.*;
import org.springframework.data.domain.Page;
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

    public void AnswerAccountResDto(Answer answer) {
        this.account.setId(answer.getId());
    }


    public AnswerResDto(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.totalVote = answer.getTotalVote();
        this.account = new AnswerAccountResDto(
                answer.getAccount().getId(),
                        answer.getAccount().getEmail(),
                        answer.getAccount().getProfile(),
                        answer.getAccount().getNickname()
        );
        setCreatedAt(answer.getCreatedAt());
        setModifiedAt(answer.getModifiedAt());
    }
}
