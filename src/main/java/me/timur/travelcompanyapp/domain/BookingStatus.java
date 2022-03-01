package me.timur.travelcompanyapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.converter.LangToJsonConverter;
import me.timur.travelcompanyapp.model.Lang;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_status")
public class BookingStatus {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = LangToJsonConverter.class)
    private Lang lang;
}