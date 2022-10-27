package stackoverflow.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SingleResDto<T> {

    T data;
}
