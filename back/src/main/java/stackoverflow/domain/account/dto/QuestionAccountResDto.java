package stackoverflow.domain.account.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;

@Getter
@Setter
@NoArgsConstructor
public class QuestionAccountResDto {

    private Long id;

    private String email;

    private String profile;

    private String nickname;

    public QuestionAccountResDto(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.profile = account.getProfile();
        this.nickname = account.getNickname();
    }
}
