package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.util.converter.LangToJsonConverter;
import me.timur.travelcompanyapp.model.Lang;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accommodation_category")
public class AccommodationCategory {

    @Id
    private String name;

    @Convert(converter = LangToJsonConverter.class)
    private Lang lang;
}