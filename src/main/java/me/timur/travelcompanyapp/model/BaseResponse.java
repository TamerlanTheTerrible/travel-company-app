package me.timur.travelcompanyapp.model;

import lombok.*;

/**
 * Created by Temurbek Ismoilov on 07/02/22.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private Object payload = null;
    private ErrorPayload error = null;

    public static BaseResponse payload(Object payload){
        return BaseResponse.builder()
                .payload(payload)
                .build();
    }

    public static BaseResponse error(Exception e) {
        return BaseResponse.builder()
                .error(new ErrorPayload(e))
                .build();
    }
}

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
class ErrorPayload {
    private String name;
    private String type;
    private String message;

    public ErrorPayload(Exception e) {
        this.name = e.getClass().getSimpleName();
        this.type = e.getClass().getTypeName();
        this.message = e.getMessage();
    }
}
