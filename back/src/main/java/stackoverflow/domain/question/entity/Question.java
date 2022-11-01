package stackoverflow.domain.question.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import stackoverflow.domain.account.entity.Account;
import stackoverflow.domain.answer.entity.Answer;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class Question extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionVote> questionVotes;

    public Question(Long id) {
        this.id = id;
    }

    @Builder
    public Question(Long id, String title, String content, Account account) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.account = account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void modify(Question question) {
        Optional.ofNullable(question.getTitle()).ifPresent(title -> this.title = title);
        Optional.ofNullable(question.getContent()).ifPresent(content -> this.content = content);
    }
}
