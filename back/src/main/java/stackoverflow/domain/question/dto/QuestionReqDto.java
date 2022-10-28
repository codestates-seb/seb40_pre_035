package stackoverflow.domain.question.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.question.entity.Question;

import javax.validation.constraints.NotBlank;

@Data
public class QuestionReqDto {

    @NotBlank
    private String title;

    @Length(min = 50)
    private String content;

    private Long accountId;

    public Question toQuestion() {
        return new Question(title, content, new Account(this.accountId));
    }
}
