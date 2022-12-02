package com.iportfolio.readingnportalnotice.application;

import com.iportfolio.readingnportalnotice.domain.Notice;
import com.iportfolio.readingnportalnotice.domain.NoticeRepository;
import com.iportfolio.readingnportalnotice.dto.response.NoticeDetailResponse;
import com.iportfolio.readingnportalnotice.dto.response.NoticeResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(final NoticeRepository noticeRepository) {
        log.info("{} Constructor called..", this.getClass().getName());
        this.noticeRepository = noticeRepository;
    }

    public NoticeResponses getActivatedNotices(final Pageable pageable, final String keyword, final Integer activate) {
        return NoticeResponses.of(noticeRepository.findByKeyword(pageable, keyword, activate));
    }

    public NoticeDetailResponse getNoticeDetail(@PathVariable final Long postId) {
        Notice notice = noticeRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        return NoticeDetailResponse.from(notice);
    }
}
