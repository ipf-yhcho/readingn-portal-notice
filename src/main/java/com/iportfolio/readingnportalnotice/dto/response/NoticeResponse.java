package com.iportfolio.readingnportalnotice.dto.response;

import com.iportfolio.readingnportalnotice.domain.Notice;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NoticeResponse implements Serializable {

    private static final long serialVersionUID = 8873608209660006156L;

    private final Long id;

    private final String title;

    private final String content;

    private final Integer activate;

    private final LocalDateTime regDateTime;

    public NoticeResponse(final Long id, final String title, final String content,
        final Integer activate, final LocalDateTime regDateTime) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.activate = activate;
        this.regDateTime = regDateTime;
    }

    public static NoticeResponse from(Notice notice) {
        return new NoticeResponse(
            notice.getId(),
            notice.getTitle(),
            notice.getContent(),
            notice.getActivate(),
            notice.getRegTime()
        );
    }
}