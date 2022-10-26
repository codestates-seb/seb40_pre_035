package stackoverflow.global.auditing;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseBy extends BaseTime{

    private String createdBy;

    private String modifiedBy;
}
