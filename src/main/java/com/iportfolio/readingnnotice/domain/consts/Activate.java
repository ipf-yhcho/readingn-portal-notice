package com.iportfolio.readingnnotice.domain.consts;

import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum Activate {

    ACTIVATED(1), NON_ACTIVATED(0);

    private final Integer code;

    public boolean compareCode(final int code) {
        return this.code.equals(code);
    }

    public static Activate convertToActivate(int code) {
        return Arrays.stream(Activate.values())
            .filter(activate -> activate.compareCode(code))
            .findFirst()
            .orElse(null);
    }
}
