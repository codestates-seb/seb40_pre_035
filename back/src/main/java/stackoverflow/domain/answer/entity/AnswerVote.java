package stackoverflow.domain.answer.entity;

import lombok.*;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.global.auditing.BaseTime;
import stackoverflow.global.common.enums.VoteState;

import javax.persistence.*;

@NoArgsConstructor
@Entity @Getter @Setter
public class AnswerVote extends BaseTime {
    @Id @GeneratedValue
    @Column(name = "answerVote_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private VoteState state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

}