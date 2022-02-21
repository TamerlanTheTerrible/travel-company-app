package me.timur.travelcompanyapp.domain.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.converter.LangToJsonConverter;
import me.timur.travelcompanyapp.model.Lang;
import org.springframework.security.core.Transient;

import javax.persistence.*;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Transient
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class ApplicationType {

    @Id
    private String type;

    @Convert(converter = LangToJsonConverter.class)
    private Lang lang;
}

