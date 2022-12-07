package com.iportfolio.readingnportalnotice.dto.request;

import com.iportfolio.readingnportalnotice.domain.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

/**
 * CREATE TABLE `t_notice` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'primary key\n',
 *   `type` int(11) NOT NULL DEFAULT '0' COMMENT '노출 타입\\n1 : 메인에 노출할 특별공지\\n0: 일반공지',
 *   `idx` int(11) NOT NULL DEFAULT '0' COMMENT '특별공지의 노출 순서',
 *   `title` varchar(100) NOT NULL COMMENT '공지사항 타이틀\n\n타이틀은 유일 값\n',
 *   `thumbnail` varchar(200) DEFAULT NULL,
 *   `description` varchar(500) DEFAULT NULL,
 *   `content` longtext NOT NULL COMMENT '공지사항 상세 내역\n\nHTML 에디터로 작성하여 데이터가 매우 큼\n',
 *   `activate` tinyint(4) NOT NULL DEFAULT '0' COMMENT '공지사항 노출 여부\n\n0: 비노출\n1: 노출\n\n\n',
 *   `reg_time` datetime NOT NULL COMMENT '등록 일자\n',
 *   `start_time` datetime DEFAULT NULL,
 *   `end_time` datetime DEFAULT NULL,
 *   `send_time` datetime DEFAULT NULL COMMENT '공지사항 푸시 전송 요청 시각',
 *   `send_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '공지사항 푸시 전송 결과 (-1: 실패, 0: 미발송, 1: 성공, 2: 발송중)',
 *   `send_target` varchar(10) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=597 DEFAULT CHARSET=utf8;
 */

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
    @Column(unique = true)
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
    @Column(columnDefinition = "longtext")
    @NotBlank(message = "content cannot be empty")
    private String content;

    @ApiModelProperty(value = "공지사항 노출 여부, (0: 비노출, 1: 노출)")
    @NotNull(message = "activate cannot be empty")
    private Short activate;

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
        return Notice.builder()
            .type(type)
            .idx(idx)
            .title(title)
            .thumbnail(thumbnail)
            .description(description)
            .content(content)
            .activate(activate)
            .sendTime(startTime)
            .endTime(endTime)
            .build();
    }
}
