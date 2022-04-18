package me.timur.travelcompanyapp.util.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import me.timur.travelcompanyapp.model.Lang;

import javax.persistence.AttributeConverter;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

public class LangToJsonConverter implements AttributeConverter<Lang, String> {

    private ObjectMapper mapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Lang lang) {
        return mapper.writeValueAsString(lang);
    }

    @SneakyThrows
    @Override
    public Lang convertToEntityAttribute(String s) {
        return mapper.readValue(s, Lang.class);
    }
}
