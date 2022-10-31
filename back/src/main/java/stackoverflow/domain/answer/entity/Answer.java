package stackoverflow.domain.answer.entity;

import lombok.*;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.question.entity.Question;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.*;

@Getter @Setter
@Entity
public class Answer extends BaseTime {
    @Id @Column(name = "answer_id")
    @GeneratedValue
    private Long id;

    private String content;

    private int totalVote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    public void setAccount(Account account) {
        this.account = account;
    }
}
