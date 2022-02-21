package me.timur.travelcompanyapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accommodation")
public class Accommodation extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "location")
    private Location location;
}