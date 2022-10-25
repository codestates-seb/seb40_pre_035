package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import stackoverflow.domain.account.dto.AnswerAccountRes;
import stackoverflow.domain.answer.entity.Answer;

@Data
@Setter
@AllArgsConstructor
public class AnswerResponseDto {
    private Long answerId;
    private String title;
    private String content;
    private int totalVote;
    private AnswerAccountRes account;


    public AnswerResponseDto(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.title = answer.getTitle();
        this.content = answer.getContent();
        this.totalVote = answer.getTotalVote();

        // Mock account
        AnswerAccountRes mockAccount = new AnswerAccountRes();
        mockAccount.setId(answerId);
        mockAccount.setEmail("mock@gmail.com");
        mockAccount.setPath("mock");
        mockAccount.setNickname("mockName");
        this.account = mockAccount;

    }
}
