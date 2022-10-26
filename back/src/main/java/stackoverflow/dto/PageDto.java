package stackoverflow.global.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
public class PageDto<T> {

    private List<T> content;

    private int totalPages;

    private long totalElements;

    private boolean first;

    private boolean last;

    private boolean sorted;

    private int size;

    private int pageNumber;

    private int numberOfElements;

    public PageDto(Page<T> page) {
        content = page.getContent();
        totalPages = page.getTotalPages();
        totalElements = page.getTotalElements();
        first = page.isFirst();
        last = page.isLast();
        sorted = page.getSort().isSorted();
        size = page.getSize();
        pageNumber = page.getNumber();
        numberOfElements = page.getNumberOfElements();
    }
}