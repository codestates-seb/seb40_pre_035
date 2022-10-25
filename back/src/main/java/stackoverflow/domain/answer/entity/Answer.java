package stackoverflow.domain.answer.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;

    @Column(length = 100, nullable = false) // 길이 제한 할 것인지 확인
    private String title;

    @Column(length = 3000, nullable = false) // 길이 제한 할 것인지 확인
    private String content;

    private int totalVote;
}
