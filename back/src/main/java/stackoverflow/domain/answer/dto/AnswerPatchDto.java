package stackoverflow.domain.answer.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.answer.entity.Answer;

@Getter
@Setter
public class AnswerPatchDto {
    private Long answerId;
    private String title;
    private String content;
}
