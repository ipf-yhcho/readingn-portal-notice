package com.iportfolio.readingnportalnotice.dto.response;


import com.iportfolio.readingnportalnotice.domain.Notice;
import com.iportfolio.readingnportalnotice.dto.response.template.PageInfo;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.domain.Page;

@Getter
@ToString
public class NoticeResponses implements Serializable {

    private static final long serialVersionUID = -7652374761863763491L;

    private final List<NoticeResponse> noticeList;

    private final PageInfo pageInfo;

    public NoticeResponses(final List<Notice> notices, final Integer pageNumber, final Integer pageSize, final Integer totalPage, final Long totalElement) {
        this.noticeList = notices.stream()
                .map(NoticeResponse::from)
                .collect(Collectors.toUnmodifiableList());

        this.pageInfo = new PageInfo(pageNumber, pageSize, totalPage, totalElement);
    }

    public static NoticeResponses of(final Page<Notice> page) {
        return new NoticeResponses(
            page.getContent(),
            page.getNumber() + 1,
            page.getSize(),
            page.getTotalPages(),
            page.getTotalElements()
        );
    }
}
