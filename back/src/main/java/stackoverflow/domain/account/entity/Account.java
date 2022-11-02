package stackoverflow.domain.account.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Account extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    private String email;

    private String password;

    private String profile;

    private String nickname;

    private String role;

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Question> questionsList = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE)
    private List<Answer> answerList = new ArrayList<>();

    public Account(Long id) {
        this.id = id;
    }
}
