package com.iportfolio.readingnportalnotice.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NoticeRepositoryCustom {

    Page<Notice> findByKeyword(final Pageable pageable, final String keyword, final Short activate);
}
