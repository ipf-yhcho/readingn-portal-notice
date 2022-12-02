package com.iportfolio.readingnportalnotice.domain;

import org.springframework.data.repository.CrudRepository;

public interface NoticeRepository extends CrudRepository<Notice, Long>, NoticeRepositoryCustom {

}
