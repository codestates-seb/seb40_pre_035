package stackoverflow.domain.question.entity;

import lombok.Getter;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.global.auditing.BaseTime;
import stackoverflow.global.common.enums.VoteState;

import javax.persistence.*;

@Entity
@Getter
public class QuestionVote extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "questionVote_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}
