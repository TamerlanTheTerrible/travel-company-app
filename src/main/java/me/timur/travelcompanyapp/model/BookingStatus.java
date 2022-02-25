package me.timur.travelcompanyapp.model;

import me.timur.travelcompanyapp.converter.LangToJsonConverter;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booking_status")
public class BookingStatus {

    @Id
    private String name;

    @Convert(converter = LangToJsonConverter.class)
    private Lang lang;
}