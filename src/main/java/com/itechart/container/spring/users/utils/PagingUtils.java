package com.itechart.container.spring.users.utils;

import lombok.experimental.UtilityClass;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class PagingUtils {

    public static Pageable toPageable(final int page, final int size) {
        return toPageable(page, size, Sort.unsorted());
    }

    public static Pageable toPageable(final int page, final int size, final Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }

}
