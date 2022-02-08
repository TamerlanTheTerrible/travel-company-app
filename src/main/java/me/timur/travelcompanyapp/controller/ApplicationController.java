package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.service.ApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@RestController
@RequestMapping("/application")
public record ApplicationController(
        ApplicationService applicationService
) {

    @GetMapping("/type")
    public BaseResponse getApplicationTypes() {
        return BaseResponse.payload (applicationService.findAllTypes());
    }
}
