package com.iportfolio.readingnportalnotice.dto.request;

import com.iportfolio.readingnportalnotice.domain.Notice;
import com.iportfolio.readingnportalnotice.domain.consts.Activate;
import com.iportfolio.readingnportalnotice.domain.consts.SendStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

@ApiModel
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CreateNoticeRequest {

    @ApiModelProperty(value = "노출 타입 (1 : 메인에 노출할 특별공지, 0: 일반공지)")
    @NotNull(message = "type cannot be empty")
    private Integer type;

    @ApiModelProperty(value = "특별공지의 노출 순서")
    @NotNull(message = "idx cannot be empty")
    private Integer idx;

    @ApiModelProperty(value = "공지사항 타이틀, 타이틀은 유일 값")
    @NotBlank(message = "title cannot be empty")
    @Length(max = 100, message = "title can be up to 100 characters")
    private String title;

    @ApiModelProperty(value = "공지사항 썸네일")
    @Nullable
    @Length(max = 200, message = "thumbnail can be up to 200 characters")
    private String thumbnail;

    @ApiModelProperty
    @Nullable
    @Length(max = 500, message = "description can be up to 500 characters")
    private String description;

    @ApiModelProperty(value = "공지사항 상세 내역")
    @NotBlank(message = "content cannot be empty")
    private String content;

    @ApiModelProperty(value = "공지사항 노출 여부, (0: 비노출, 1: 노출)")
    @NotNull(message = "activate cannot be empty")
    private Integer activate;

    @ApiModelProperty(value = "공지 시작일")
    @Nullable
    private LocalDateTime startTime;

    @ApiModelProperty(value = "공지 종료일")
    @Nullable
    private LocalDateTime endTime;

    @ApiModelProperty(value = "공지사항 푸시 전송")
    @Nullable
    @Length(max = 10, message = "sendTarget can be up to 10 characters")
    private String sendTarget;

    public Notice toNoticeEntity() {
        LocalDateTime now = LocalDateTime.now();

        return Notice.builder()
            .type(type)
            .idx(idx)
            .title(title)
            .thumbnail(thumbnail)
            .description(description)
            .content(content)
            .activate(Activate.convertToActivate(activate))
            .regTime(now) // TODO
            .startTime(startTime)
            .endTime(endTime)
            .sendTime(now) // TODO
            .sendStatus(SendStatus.UNSENT) // TODO
            .sendTarget(sendTarget)
            .build();
    }
}
