package me.timur.travelcompanyapp.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lang {
    private String en;
    private String ru;
}
