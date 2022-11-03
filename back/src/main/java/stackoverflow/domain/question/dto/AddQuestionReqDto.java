package stackoverflow.domain.question.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.question.entity.Question;

import javax.validation.constraints.NotBlank;

@Data
public class AddQuestionReqDto {

    @NotBlank
    private String title;

    @Length(min = 50)
    private String content;

    public Question toQuestion(Long accountId) {
        return Question.builder()
                .title(title)
                .content(content)
                .account(new Account(accountId))
                .build();
    }
}
