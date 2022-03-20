package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.entity.Application;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.model.reservation.pre.ApplicationPreRegistrationRequest;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<String, String> filters = new HashMap<>();
        for (String key: parameterMap.keySet()){
            filters.put(key, parameterMap.get(key)[0]);
        }
        filters.put("tourOperator", tourOperator.getUsername());

        return BaseResponse.payload(applicationService.findAllFiltered(filters));
    }

    @GetMapping("/type")
    public BaseResponse getApplicationTypes() {
        return BaseResponse.payload(applicationService.findAllTypes());
    }
}
