package stackoverflow.global.auditing;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public abstract class BaseTime {

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;
}
