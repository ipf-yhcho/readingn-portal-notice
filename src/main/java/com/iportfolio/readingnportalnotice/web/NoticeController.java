package com.iportfolio.readingnportalnotice.web;

import static com.iportfolio.readingnportalnotice.domain.consts.NoticeConst.ACTIVATED;

import com.iportfolio.readingnportalnotice.application.NoticeService;
import com.iportfolio.readingnportalnotice.dto.response.NoticeDetailResponse;
import com.iportfolio.readingnportalnotice.dto.response.NoticeResponses;
import com.iportfolio.readingnportalnotice.dto.response.template.ApiResponse;
import com.iportfolio.readingnportalnotice.dto.response.template.ResponseCode;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/notices")
public class NoticeController {

    private final NoticeService noticeService;

    public NoticeController(final NoticeService noticeService) {
        log.info("{} Constructor called..", this.getClass().getName());
        this.noticeService = noticeService;
    }

    @ApiOperation(
        value = "",
        produces = "application/json",
        response = ApiResponse.class
    )
    @GetMapping("/qna-action")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<NoticeResponses> getActivatedNotices(
        @RequestParam(defaultValue = "1") final Integer page,
        @RequestParam(defaultValue = "10") final Integer limit,
        String keyword) {

        NoticeResponses response = noticeService.getActivatedNotices(
            PageRequest.of(page - 1, limit), keyword, ACTIVATED);

        return new ApiResponse<>(response, ResponseCode.OK);
    }

    @ApiOperation(
        value = "",
        produces = "application/json",
        response = ApiResponse.class
    )
    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<NoticeDetailResponse> getNoticeDetail(@PathVariable final Long postId) {
        NoticeDetailResponse response = noticeService.getNoticeDetail(postId);

        return new ApiResponse<>(response, ResponseCode.OK);
    }
}
