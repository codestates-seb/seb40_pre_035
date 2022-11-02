package stackoverflow.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.global.common.dto.PageDto;

@Getter
@Builder
public class ProfileAccountResDto {
    private String email;
    private String nickname;
    private String profile;
    private Long totalVotes;
    private Long totalQuestions;
    private Long totalAnswers;
}