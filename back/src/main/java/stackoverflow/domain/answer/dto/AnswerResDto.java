package stackoverflow.domain.answer.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import stackoverflow.domain.account.dto.AnswerAccountResDto;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.global.auditing.BaseTime;

@NoArgsConstructor @AllArgsConstructor
@Builder @Getter @Setter
public class AnswerResDto extends BaseTime {
    private Long id;

    private String content;

    private int totalVote;

    private AnswerAccountResDto account;

    public Page<AnswerResDto> toDtoList(Page<Answer> answerList) {
        Page<AnswerResDto> answerResDtoPage = answerList.map(answer -> AnswerResDto
                .builder()
                .id(answer.getId())
                .content(answer.getContent())
                .totalVote(answer.getTotalVote())
                .account(new AnswerAccountResDto(
                        answer.getAccount().getId(),
                        answer.getAccount().getEmail(),
                        answer.getAccount().getProfile(),
                        answer.getAccount().getNickname())
                )
                .build());
        return answerResDtoPage;
    }
}
