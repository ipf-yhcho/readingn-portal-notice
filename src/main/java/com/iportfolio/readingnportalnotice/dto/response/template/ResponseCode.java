package com.iportfolio.readingnportalnotice.dto.response.template;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public enum ResponseCode {

    OK(HttpStatus.OK);

    private final Integer status;

    private final String message;

    ResponseCode(final HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
    }
}
