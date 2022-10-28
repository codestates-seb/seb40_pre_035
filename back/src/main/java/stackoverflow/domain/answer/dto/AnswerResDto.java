package stackoverflow.domain.answer.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.global.auditing.BaseTime;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResDto extends BaseTime {
    private Long id;

    private String content;

    private int totalVote;

    private AnswerAccountResDto account;
}
