package stackoverflow.domain.question.dto;

import lombok.Data;
import stackoverflow.global.common.enums.VoteState;

@Data
public class AddQuestionVoteReqDto {

    private VoteState state;
}
