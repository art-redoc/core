package art.redoc.core.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

@Getter
@Setter
public class PageData {

    private long totalElements;
    private int numberOfElements;
    private int totalPages;
    private int page;
    private boolean first;
    private boolean last;
    private int size;

    public static PageData convert(final Page<?> page) {
        final PageData pageData = new PageData();
        pageData.setFirst(page.isFirst());
        pageData.setLast(page.isLast());
        pageData.setPage(page.getNumber());
        pageData.setTotalPages(page.getTotalPages());
        pageData.setNumberOfElements(page.getNumberOfElements());
        pageData.setSize(page.getSize());
        pageData.setTotalElements(page.getTotalElements());
        return pageData;
    }
}
