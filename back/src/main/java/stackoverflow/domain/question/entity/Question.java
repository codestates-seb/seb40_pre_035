package stackoverflow.domain.question.entity;

import lombok.Getter;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Question extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    @OneToMany(mappedBy = "question")
    private List<QuestionVote> questionVotes;
}
