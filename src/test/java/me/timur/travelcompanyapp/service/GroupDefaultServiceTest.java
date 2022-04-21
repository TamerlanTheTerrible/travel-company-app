package me.timur.travelcompanyapp.service;

import me.timur.travelcompanyapp.entity.Company;
import me.timur.travelcompanyapp.entity.Group;
import me.timur.travelcompanyapp.entity.User;
import me.timur.travelcompanyapp.repository.CompanyRepository;
import me.timur.travelcompanyapp.repository.GroupRepository;
import me.timur.travelcompanyapp.repository.UserRepository;
import me.timur.travelcompanyapp.security.auth.ApplicationUserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(locations ="classpath:application-test.properties")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class GroupDefaultServiceTest {

    @Autowired
    GroupService groupService;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    UserRepository userRepository;

    Group group1;
    Company company1;
    User tourOperator1;

    @BeforeEach
    void setUp() {
        company1 = new Company();
        company1.setId(1);
        company1.setCountry("Poland");
        company1.setName("Krzysztof Matys");
        companyRepository.save(company1);

        tourOperator1 = new User();
        tourOperator1.setId(1);
        tourOperator1.setRole(ApplicationUserRole.TOUR_OPERATOR);
        tourOperator1.setUsername("jackie-chan");
        userRepository.save(tourOperator1);

        group1 = new Group();
        group1.setId(1);
        group1.setIsActive(true);
        group1.setGroupNumber("1/1-I");
        group1.setArrival(LocalDateTime.now().plusMonths(1));
        group1.setDeparture(LocalDateTime.now().plusMonths(1).plusDays(12));
        group1.setCompany(company1);
        group1.setCountry("Poland");
        group1.setRegisteredSize((short) 18);
        group1.setTourOperator(tourOperator1);
        groupRepository.save(group1);
    }

    @Test
    void register() {
    }

    @Test
    void cancel() {
    }

    @Test
    void findById() {
        //when
        final Group group = groupService.findById(1);

        //then
        assertEquals(1, group.getId());
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void findByIdAndUser() {
    }
}