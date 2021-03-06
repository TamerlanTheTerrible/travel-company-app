package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.util.converter.LangToJsonConverter;
import me.timur.travelcompanyapp.model.Lang;

import javax.persistence.*;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Location {

    @Id
    private String name;

    @Convert(converter = LangToJsonConverter.class)
    private Lang lang;
}
