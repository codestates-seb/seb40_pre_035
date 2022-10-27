package stackoverflow.domain.answer.dto;

import lombok.*;
import stackoverflow.domain.answer.entity.Answer;


@Getter
@Setter
public class AnswerReqDto {

    private String title;

    private String content;

    private int totalVote;

}
