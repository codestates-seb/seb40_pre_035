package stackoverflow.domain.answer.entity;

import lombok.*;
import stackoverflow.global.auditing.BaseTime;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Answer extends BaseTime {
    @Id
    @Column(name = "answer_id")
    @GeneratedValue
    private Long id;

    private String content;

    private int totalVote;

}
