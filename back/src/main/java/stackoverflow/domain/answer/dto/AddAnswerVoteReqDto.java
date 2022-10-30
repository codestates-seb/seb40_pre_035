package stackoverflow.domain.answer.dto;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.global.common.enums.VoteState;

@Getter
@Setter
public class AddAnswerVoteReqDto {

    private VoteState state;

}
