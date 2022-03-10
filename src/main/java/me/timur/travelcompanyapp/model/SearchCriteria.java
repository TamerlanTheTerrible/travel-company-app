package me.timur.travelcompanyapp.model;

import lombok.Data;

/**
 * Created by Temurbek Ismoilov on 03/03/22.
 */

@Data
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}