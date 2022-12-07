package com.iportfolio.readingnportalnotice.application;

import com.iportfolio.readingnportalnotice.domain.Notice;
import com.iportfolio.readingnportalnotice.domain.NoticeRepository;
import com.iportfolio.readingnportalnotice.domain.consts.Activate;
import com.iportfolio.readingnportalnotice.dto.request.CreateNoticeRequest;
import com.iportfolio.readingnportalnotice.dto.request.ModifyNoticeRequest;
import com.iportfolio.readingnportalnotice.dto.response.NoticeDetailResponse;
import com.iportfolio.readingnportalnotice.dto.response.NoticeResponses;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Service
@Transactional
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public NoticeService(final NoticeRepository noticeRepository) {
        log.info("{} Constructor called..", this.getClass().getName());
        this.noticeRepository = noticeRepository;
    }

    /**
     *
     * @param pageable
     * @param keyword
     * @param activate
     * @return
     */
    @Transactional(readOnly = true)
    public NoticeResponses getActivatedNotices(final Pageable pageable, final String keyword,
        final Activate activate) {

        return NoticeResponses.of(noticeRepository.findByKeyword(pageable, keyword, activate));
    }

    /**
     *
     * @param postId
     * @return
     */
    @Transactional(readOnly = true)
    public NoticeDetailResponse getNoticeDetail(@PathVariable final Long postId) {
        Notice notice = noticeRepository.findById(postId)
            .orElseThrow(RuntimeException::new);

        return NoticeDetailResponse.from(notice);
    }

    /**
     *
     * @param createNoticeRequest
     */
    public void createNotice(
        @Valid @RequestBody CreateNoticeRequest createNoticeRequest) {

        Notice notice = noticeRepository.save(createNoticeRequest.toNoticeEntity());

        log.info("NoticeService.createNotice: {} is created", notice);
    }

    /**
     *
     * @param postId
     * @param modifyNoticeRequest
     */
    public void modifyNotice(@PathVariable Long postId,
        @Valid @RequestBody ModifyNoticeRequest modifyNoticeRequest) {

        Notice notice = noticeRepository.findById(postId)
            .orElseThrow(RuntimeException::new);

        notice.modifyFields(modifyNoticeRequest.toNoticeEntity());

        log.info("NoticeService.modifyNotice: {} is modified", notice);
    }

    /**
     *
     * @param postId
     */
    public void deleteNotice(@PathVariable Long postId) {

        noticeRepository.deleteById(postId);
    }
}
