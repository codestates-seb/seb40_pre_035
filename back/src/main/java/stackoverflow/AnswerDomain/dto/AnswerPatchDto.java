package stackoverflow.AnswerDomain.dto;

import lombok.Data;

@Data
public class AnswerPatchDto {
    private Long answerId;
    private Long accountId;
    private Long questionId;

    private String title;
    private String content;
}
