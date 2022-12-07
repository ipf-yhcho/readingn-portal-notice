package com.iportfolio.readingnportalnotice.domain;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "t_notice")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer type;

    @NotNull
    private Integer idx;

    @Column(length = 100)
    @NotBlank
    private String title;

    @Column(length = 200)
    @NotBlank
    private String thumbnail;

    @Column(length = 500)
    @NotBlank
    private String description;

    @NotBlank
    private String content;

    @NotNull
    private Short activate;

    @NotNull
    private LocalDateTime regTime;

    @Nullable
    private LocalDateTime startTime;

    @Nullable
    private LocalDateTime endTime;

    @Nullable
    private LocalDateTime sendTime;

    @Column(length = 4)
    @NotNull
    private Integer sendStatus;

    @Column(length = 10)
    private String sendTarget;

    @Builder
    private Notice(final Long id, final Integer type, final Integer idx, final String title,
        final String thumbnail, final String description, final String content,
        final Short activate, final LocalDateTime regTime,
        @Nullable final LocalDateTime startTime, @Nullable final LocalDateTime endTime,
        @Nullable final LocalDateTime sendTime, final Integer sendStatus, final String sendTarget) {

        this.id = id;
        this.type = type;
        this.idx = idx;
        this.title = title;
        this.thumbnail = thumbnail;
        this.description = description;
        this.content = content;
        this.activate = activate;
        this.regTime = regTime;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sendTime = sendTime;
        this.sendStatus = sendStatus;
        this.sendTarget = sendTarget;
    }

    public static Notice of(final Long id, final Integer type, final Integer idx, final String title,
        final String thumbnail, final String description, final String content,
        final Short activate, final LocalDateTime regTime,
        @Nullable final LocalDateTime startTime, @Nullable final LocalDateTime endTime,
        @Nullable final LocalDateTime sendTime, final Integer sendStatus, final String sendTarget) {

        return Notice.builder()
            .id(id)
            .type(type)
            .idx(idx)
            .title(title)
            .thumbnail(thumbnail)
            .description(description)
            .content(content)
            .activate(activate)
            .regTime(regTime)
            .startTime(startTime)
            .endTime(endTime)
            .sendTime(sendTime)
            .sendStatus(sendStatus)
            .sendTarget(sendTarget)
            .build();
    }
}
