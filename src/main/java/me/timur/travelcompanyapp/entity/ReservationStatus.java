package me.timur.travelcompanyapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.timur.travelcompanyapp.util.converter.LangToJsonConverter;
import me.timur.travelcompanyapp.model.Lang;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation_status")
public class ReservationStatus {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Convert(converter = LangToJsonConverter.class)
    private Lang lang;
}