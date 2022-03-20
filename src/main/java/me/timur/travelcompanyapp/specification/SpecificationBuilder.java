package me.timur.travelcompanyapp.specification;

import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Set;

import static org.springframework.data.jpa.domain.Specification.where;

/**
 * Created by Temurbek Ismoilov on 16/03/22.
 */


public class SpecificationBuilder<T> {
    public static <T> Specification<T> build(EntitySpecification entitySpecification, HashMap<String, String> filters) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Specification<T> specification = null;

        final Set<String> keySet = filters.keySet();

        if (keySet.size() == 0)
            return emptySpecification();

        for (String key: keySet) {
            if (specification == null)
                specification = where(invokeMethod(entitySpecification, filters, key));
            else
                specification = specification.and(invokeMethod(entitySpecification, filters, key));
        }

        return specification;
    }

    private static <T> Specification<T> invokeMethod(EntitySpecification entitySpecification, HashMap<String, String> filters, String key) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        return (Specification<T>) ApplicationSpecification.class.getDeclaredMethod(key, String.class).invoke(entitySpecification, filters.get(key));
    }

    private static <T> Specification<T> emptySpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}
