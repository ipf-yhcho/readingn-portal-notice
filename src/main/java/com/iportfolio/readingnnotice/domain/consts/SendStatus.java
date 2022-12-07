package com.iportfolio.readingnnotice.domain.consts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum SendStatus {

    FAIL(-1)
    , UNSENT(0)
    , SUCCESS(1)
    , SENDING(2);

    private final Integer code;

    public boolean compareCode(final int code) {
        return this.code.equals(code);
    }
}
