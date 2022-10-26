package stackoverflow.domain.question.entity;

import lombok.Getter;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Question extends BaseTime {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    private String title;

    private String content;

    private int totalVote;

    private Long accountId;
}
