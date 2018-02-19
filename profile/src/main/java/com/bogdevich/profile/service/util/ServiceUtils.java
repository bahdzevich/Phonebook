package com.bogdevich.profile.service.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ServiceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceUtils.class);

    /**
     * (just 4 lulz)
     * Copy all fields values (instead of values of ignoring fields).
     * Getters and setters are necessary.
     * @param target (to object)
     * @param source (from object)
     */
    public static <T> void copyProperties(
            final T target, final T source,
            final String... propertiesToIgnore) {

        requireNonNull(target, "Target object is null");
        requireNonNull(source, "Source object is null");
        if (target.getClass() != source.getClass()) {
            throw new IllegalArgumentException("Source class is different from target class.");
        }
        /* Collect source object getters */
        List<Method> sourceGettersList = Arrays
                .stream(source.getClass().getMethods())
                .filter(method -> method.getName().startsWith("get"))
                .filter(method -> ! method.getName().equals("getClass"))
                .collect(Collectors.toList());
        for (Method getter : sourceGettersList) {
            try {
                if (Arrays.stream(propertiesToIgnore)
                        .anyMatch(s -> s.equalsIgnoreCase(getter
                                .getName().replaceFirst("get","")))) {
                    continue;
                }
                Object value = getter.invoke(source);
                target.getClass().getMethod(
                                getter.getName().replaceFirst("get", "set"),
                                getter.getReturnType()).invoke(target, value);
            } catch (IllegalAccessException ex) {
                LOGGER.warn("Copy properties: illegal method access", ex);
            } catch (InvocationTargetException ex) {
                LOGGER.warn("Copy properties: invalid method invocation", ex);
            } catch (NoSuchMethodException ex) {
                LOGGER.warn("Copy properties: method is not found - " + getter.getName(), ex);
            }
        }
    }

    /**
     * Check reference for nullity.
     *
     * @param obj   the reference to check for nullity.
     * @param message   the message for thrown exception
     * @throws IllegalArgumentException if {@code obj} is {@code null}
     */
    public static <T> T requireNonNull(T obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
        return obj;
    }
}
