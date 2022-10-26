package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.global.auditing.BaseTime;

@Data
@Setter
@AllArgsConstructor
public class AnswerResDto extends BaseTime {
    private Long id;
    private String title;
    private String content;
    private int totalVote;
    private AnswerAccountResDto account;


//    public AnswerResDto(Answer answer) {
//        this.id = answer.getId();
//        this.title = answer.getTitle();
//        this.content = answer.getContent();
//        this.totalVote = answer.getTotalVote();
//
//        // Mock account
//        AnswerAccountResDto mockAccount = new AnswerAccountResDto();
//        mockAccount.setId(id);
//        mockAccount.setEmail("mock@gmail.com");
//        mockAccount.setPath("mock");
//        mockAccount.setNickname("mockName");
//        this.account = mockAccount;
//
//    }
}
