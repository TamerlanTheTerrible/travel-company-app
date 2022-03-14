package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.domain.User;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.model.reservation.GroupRegistrationRequest;
import me.timur.travelcompanyapp.service.GroupService;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@RestController
@RequestMapping("/group")
public record GroupController(
        GroupService groupService
) {

    @PostMapping("")
    public BaseResponse register(
            @RequestBody GroupRegistrationRequest groupRegistrationRequest,
            @AuthorizationUser User user
    ) {
        var groupId = groupService.register(groupRegistrationRequest, user);
        return BaseResponse.payload(groupId);
    }
}
