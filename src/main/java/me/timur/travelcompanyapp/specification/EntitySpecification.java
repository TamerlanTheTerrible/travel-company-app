package me.timur.travelcompanyapp.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * Created by Temurbek Ismoilov on 16/03/22.
 */

@Component
public interface EntitySpecification {
    default <T> Specification<T> emptySpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
