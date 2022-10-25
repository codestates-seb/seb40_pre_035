package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import stackoverflow.domain.answer.entity.Answer;

@Data
@Setter
@AllArgsConstructor
public class AnswerResponseDto {
    private Long answerId;
    private String title;
    private String content;

    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.title = answer.getTitle();
        this.content = answer.getContent();
    }
}
