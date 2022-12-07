package com.iportfolio.readingnnotice.component.converter;

import com.iportfolio.readingnnotice.domain.consts.SendStatus;
import java.util.Arrays;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SendStatusAttributeConverter implements AttributeConverter<SendStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SendStatus status) {
        if (status == null) {
            return null;
        }

        return status.getCode();
    }

    @Override
    public SendStatus convertToEntityAttribute(Integer code) {
        if (code == null) {
            return null;
        }

        return Arrays.stream(SendStatus.values())
            .filter(status -> status.compareCode(code))
            .findFirst()
            .orElse(null);
    }
}
