package me.timur.travelcompanyapp.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends BaseEntity{

    public Company(String name) {
        this.name = name;
    }

    private String name;

    private String country;
}