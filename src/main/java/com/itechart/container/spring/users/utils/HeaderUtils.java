package com.itechart.container.spring.users.utils;

import lombok.experimental.UtilityClass;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

@UtilityClass
public final class HeaderUtils {

    private static final String X_TOTAL_COUNT = "X-Total-Count";

    public static <T> MultiValueMap<String, String> generatePaginationHeaders(final Page<T> page) {
        var headers = new HttpHeaders();
        headers.add(X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        return headers;
    }

}
