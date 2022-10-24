package stackoverflow.AnswerDomain.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Answer {
    @Id
    private Long answerId;
    private Long accountId;
    private Long questionId;

    @Column(length = 100, nullable = false) // 길이 제한 할 것인지 확인
    private String title;
    @Column(length = 3000, nullable = false) // 길이 제한 할 것인지 확인
    private String content;

    // 추후 audit로 변경할 값
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime modifiedAt = LocalDateTime.now();
}
