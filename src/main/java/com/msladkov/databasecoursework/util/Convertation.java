package com.msladkov.databasecoursework.util;

import java.util.Optional;

public class Convertation {
    public static Optional<Long> parseLong(String s) {
        try {
            return Optional.of(Long.parseLong(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
