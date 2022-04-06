package me.timur.travelcompanyapp.controller;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.model.BaseResponse;
import me.timur.travelcompanyapp.model.reservation.post.GroupPostRegistrationDto;
import me.timur.travelcompanyapp.service.GroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GroupControllerTest {

    MockMvc mockMvc;

    @Mock
    GroupService groupService;
    @Mock
    GroupPostRegistrationDto dto;
    @Mock


    Group group1;
    Company companyKris;
    User userBruce;
    GroupController groupController;



    @BeforeEach
    void setUp() {
        companyKris = Company.builder().country("Poland").name("Krzysztof Matys").build();
        userBruce = User.builder().id(1).username("Bruce").build();
        group1 = Group.builder()
                .groupNumber("1/1-I")
                .isActive(true)
                .arrival(LocalDateTime.now())
                .departure(LocalDateTime.now())
                .tourOperator(userBruce)
                .company(companyKris)
                .country("Poland")
                .registeredSize((short) 14)
                .build();

        MockitoAnnotations.initMocks(this);
        groupController = new GroupController(groupService);
        mockMvc = MockMvcBuilders.standaloneSetup(groupController).build();

    }

    @Test
    void register() {
    }

    @Test
    void getOne() throws Exception {

        final int id = anyInt();
        when(groupService.findById(id)).thenReturn(mock(Group.class));
        MockedStatic<BaseResponse> mockResponse = mockStatic(BaseResponse.class);
        MockedStatic<GroupPostRegistrationDto> mockDto = mockStatic(GroupPostRegistrationDto.class);
        mockDto.when(() -> GroupPostRegistrationDto.fromEntity(any())).thenReturn(mock(GroupPostRegistrationDto.class));
        mockResponse.when(() -> BaseResponse.payload(any())).thenReturn(mock(BaseResponse.class));

        final MvcResult mvcResult = mockMvc.perform(get("/group/" + id))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    void edit() {
    }

    @Test
    void cancel() {
    }

    @Test
    void getAll() {
    }

    @Test
    void groupService() {
    }
}