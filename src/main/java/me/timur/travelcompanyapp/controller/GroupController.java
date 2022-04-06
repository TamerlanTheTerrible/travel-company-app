package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.annotation.AuthorizationUser;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.exception.ResourceAccessDeniedException;
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
        Group group = groupService.register(groupRegistrationRequest, user);
        return BaseResponse.payload(GroupPostRegistrationDto.fromEntity(group));
    }

    @GetMapping("/{groupId}")
    public BaseResponse getOne(@PathVariable Integer groupId, @AuthorizationUser User tourOperator) {
        final Group group = groupService.findByIdAndUser(groupId, tourOperator);
        return BaseResponse.payload(GroupPostRegistrationDto.fromEntity(group));
    }

    @PutMapping("/{groupId}")
    public BaseResponse edit(@PathVariable Integer groupId,
                             @RequestBody GroupRegistrationRequest groupRegistrationRequest,
                             @AuthorizationUser User tourOperator) {

        final Group group = groupService.update(groupId, groupRegistrationRequest, tourOperator);
        return BaseResponse.payload(GroupPostRegistrationDto.fromEntity(group));
    }

    @DeleteMapping("/{groupId}")
    public BaseResponse cancel(@PathVariable("groupId") Integer groupId, @AuthorizationUser User user){
        groupService.cancel(groupId, user);
        return BaseResponse.payload("SUCCESS");
    }

    @GetMapping("")
    public BaseResponse getAll(HttpServletRequest request, @AuthorizationUser User tourOperator) {
        final List<Group> groups = groupService.findAll(ServletRequestUtil.getParameterMap(request, tourOperator));
        return BaseResponse.payload(GroupPostRegistrationDto.toDtoList(groups));
    }

}
