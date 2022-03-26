package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.model.reservation.pre.ApplicationPreRegistrationRequest;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.service.ApplicationService;
import me.timur.travelcompanyapp.util.ServletRequestUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Temurbek Ismoilov on 08/02/22.
 */

@RestController
@RequestMapping("/application")
public record ApplicationController(
        ApplicationService applicationService
) {

    @PostMapping("")
    public BaseResponse save(@RequestBody ApplicationPreRegistrationRequest createRequest){
        Application application = applicationService.save(createRequest);
        return BaseResponse.payload(application.getId());
    }

    @GetMapping("")
    public BaseResponse getAll(HttpServletRequest request, @AuthorizationUser User tourOperator) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return BaseResponse.payload(applicationService.findAllFiltered(ServletRequestUtil.getParameterMap(request, tourOperator)));
    }

    @GetMapping("/type")
    public BaseResponse getApplicationTypes() {
        return BaseResponse.payload(applicationService.findAllTypes());
    }
}
