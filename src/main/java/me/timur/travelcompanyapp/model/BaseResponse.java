package me.timur.travelcompanyapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String code = "OK";
    private String description = null;
    private Object payload;

    public static BaseResponse payload(Object payload){
        return BaseResponse.builder()
                .payload(payload)
                .code("OK")
                .build();
    }
}
