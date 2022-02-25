package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.service.ApplicationService;
import me.timur.travelcompanyapp.service.BookingService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@RestController
@RequestMapping("/application")
public record ApplicationController(
        ApplicationService applicationService
) {

    @PostMapping("")
    public BaseResponse saveApplication(
            @RequestBody ApplicationCreateRequest createRequest,
            @AuthorizationUser User tourOperator
    ) {

        return BaseResponse.payload("hello");
    }


    @GetMapping("/type")
    public BaseResponse getApplicationTypes() {
        return BaseResponse.payload (applicationService.findAllTypes());
    }
}
