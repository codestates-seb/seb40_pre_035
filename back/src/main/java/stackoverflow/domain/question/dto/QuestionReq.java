package stackoverflow.domain.question.dto;

import lombok.Data;

@Data
public class QuestionReq {

    private String title;

    private String content;
}
