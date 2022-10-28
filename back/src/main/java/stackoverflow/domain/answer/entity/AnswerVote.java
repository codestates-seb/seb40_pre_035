package stackoverflow.domain.answer.entity;

import lombok.Data;
import stackoverflow.domain.account.entity.Account;

import javax.persistence.*;

@Data
@Entity
public class AnswerVote {
    @Id @GeneratedValue
    @Column(name = "answerVote_id")
    private Long id;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

}