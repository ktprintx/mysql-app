package com.printix.mysqlapp.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.UUID;

@Converter(autoApply = true)
public class UUIDStringConverter implements AttributeConverter<UUID, String> {

    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        if (uuid == null) return null;
        return uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String uuidString) {
        if (uuidString == null || uuidString.isEmpty()) return null;
        return UUID.fromString(uuidString);
    }
}
