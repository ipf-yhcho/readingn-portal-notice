package com.iportfolio.readingnportalnotice.domain;

import static com.iportfolio.readingnportalnotice.domain.QNotice.notice;
import static com.iportfolio.readingnportalnotice.domain.consts.NoticeConst.ACTIVATED;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@Slf4j
public class NoticeRepositoryImpl implements NoticeRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    public NoticeRepositoryImpl(final JPAQueryFactory jpaQueryFactory) {
        log.info("{} Constructor called..", this.getClass().getName());
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public Page<Notice> findByKeyword(final Pageable pageable, final String keyword, final Short activate) {
        final JPQLQuery<Notice> jpqlQuery = jpaQueryFactory
                .selectFrom(notice)
                .where(
                        containKeyword(keyword),
                        isActivated()
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        return PageableExecutionUtils.getPage(jpqlQuery.fetch(), pageable, jpqlQuery::fetchCount);
    }

    private BooleanExpression containKeyword(final String keyword) {
        if (keyword == null) {
            return null;
        }

        return notice.title.contains(keyword);
    }

    private BooleanExpression isActivated() {
        return notice.activate.eq(ACTIVATED);
    }
}
