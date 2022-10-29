package stackoverflow.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAccountResDto {

    private Long id;

    private String email;

    private String profile;

    private String nickname;
}
