package stackoverflow.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerAccountResDto {

    private Long id;

    private String email;

    private String profile;

    private String nickname;

    public AnswerAccountResDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.profile = account.getProfile();
        this.nickname = account.getNickname();
    }
}
