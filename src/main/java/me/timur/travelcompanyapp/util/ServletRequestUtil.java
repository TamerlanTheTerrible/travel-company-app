package me.timur.travelcompanyapp.util;

import me.timur.travelcompanyapp.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Temurbek Ismoilov on 25/03/22.
 */

public class ServletRequestUtil {

    public static HashMap<String, String> getParameterMap(HttpServletRequest request, User user) {
        final HashMap<String, String> filters = getParameterMap(request);
        filters.put("tourOperator", user.getUsername());

        return filters;
    }

    public static HashMap<String, String> getParameterMap(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        HashMap<String, String> filters = new HashMap<>();
        for (String key: parameterMap.keySet()){
            filters.put(key, parameterMap.get(key)[0]);
        }
        return filters;
    }

}
