package me.timur.travelcompanyapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.domain.Application;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.model.ApplicationCreateRequest;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.service.ApplicationService;
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
    ) throws JsonProcessingException {
        Application application = applicationService.save(createRequest);
        return BaseResponse.payload(new ObjectMapper().writeValueAsString(application.getId()));
    }


    @GetMapping("/type")
    public BaseResponse getApplicationTypes() {
        return BaseResponse.payload(applicationService.findAllTypes());
    }
}
