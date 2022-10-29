package stackoverflow.domain.question.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.question.entity.Question;

import javax.validation.constraints.NotBlank;

@Data
public class QuestionReqDto {

    private Long questionId;

    @NotBlank
    private String title;

    @Length(min = 50)
    private String content;

    private Long accountId;

    public Question toQuestion() {
        return Question.builder()
                .id(questionId)
                .title(title)
                .content(content)
                .account(new Account(this.accountId))
                .build();
    }
}
