package stackoverflow.domain.answer.dto;

import lombok.*;
import stackoverflow.domain.answer.entity.Answer;


@Getter
@Setter
public class AnswerPostDto {

    private Long answerId;

    private String title;

    private String content;
}
