package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.model.reservation.post.GroupPostRegistrationDto;
import me.timur.travelcompanyapp.model.reservation.pre.GroupRegistrationRequest;
import me.timur.travelcompanyapp.service.GroupService;
import me.timur.travelcompanyapp.util.ServletRequestUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Temurbek Ismoilov on 06/02/22.
 */

@RestController
@RequestMapping("/group")
public record GroupController(
        GroupService groupService
) {

    @PostMapping("")
    public BaseResponse register(@RequestBody GroupRegistrationRequest groupRegistrationRequest, @AuthorizationUser User user) {
        var groupId = groupService.register(groupRegistrationRequest, user);
        return BaseResponse.payload(groupId);
    }

    @GetMapping("")
    public BaseResponse getAll(HttpServletRequest request, @AuthorizationUser User tourOperator) {
        final List<Group> groups = groupService.findAll(ServletRequestUtil.getParameterMap(request, tourOperator));
        return BaseResponse.payload(GroupPostRegistrationDto.toDtoList(groups));
    }

    @DeleteMapping("/{groupId}")
    public BaseResponse cancel(@PathVariable("groupId") Integer groupId, @AuthorizationUser User user){
        groupService.cancel(groupId, user);
        return BaseResponse.payload("SUCCESS");
    }

}
