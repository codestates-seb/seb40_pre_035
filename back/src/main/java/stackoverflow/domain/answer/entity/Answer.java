package stackoverflow.domain.answer.entity;

import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@Entity
public class Answer extends BaseTime {
    @Id @Column(name = "answer_id")
    @GeneratedValue
    private Long id;

    private String content;

    private boolean selected;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer")
    private List<AnswerVote> answerVotes = new ArrayList<>();

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setQuestion(Question question) { this.question = question;}
}
