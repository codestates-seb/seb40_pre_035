package stackoverflow.domain.answer.dto;

import lombok.*;


@Getter
@Setter
public class AnswerRequestDto {

    private Long answerId;

    private String title;

    private String content;
}
