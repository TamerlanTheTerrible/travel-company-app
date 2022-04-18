package me.timur.travelcompanyapp.specification;

import me.timur.travelcompanyapp.exception.SpecificationBuilderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecificationBuilderTest {

    @Mock
    GroupSpecification groupSpecification;
    SpecificationBuilder specificationBuilder;

    HashMap<String, String> filters;


    @BeforeEach
    void setUp() {
        specificationBuilder = new SpecificationBuilder();
        filters = new HashMap<>();
    }

    @Test
    void doNotInvokeGroupSpecificationWhenFilterIsEmpty() {
        //given
        EntitySpecification entitySpecification = groupSpecification;

        //when
        specificationBuilder.build(entitySpecification, filters);

        //then
        verifyNoInteractions(groupSpecification);
    }

    @Test
    void invokeGroupSpecificationMatchingMethod() {
        //given
        EntitySpecification entitySpecification = groupSpecification;
        filters.put("groupNumber", "1/1-I");

        //when
        specificationBuilder.build(entitySpecification, filters);

        //then
        verify(groupSpecification).groupNumber(anyString());
        verifyNoMoreInteractions(groupSpecification);
    }

    @Test
    void throwExceptionWhenNoMatchingMethod() {
        //given
        EntitySpecification entitySpecification = groupSpecification;
        String filterName = "groupMumber";
        filters.put(filterName, "1/1-I");
        String expectedMessage = "Error occurred during building specification with filter: " + filterName;

        //when
        //then
        var exception = assertThrows(SpecificationBuilderException.class, () -> specificationBuilder.build(entitySpecification, filters));
        assertTrue(exception.getMessage().contains(expectedMessage));
        verifyNoInteractions(groupSpecification);
    }
}