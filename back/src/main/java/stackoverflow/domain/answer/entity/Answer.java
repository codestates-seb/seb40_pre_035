package stackoverflow.domain.answer.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @Column(name = "answer_id")
    private Long id;

    private String title;

    private String content;

    private int totalVote;
}
