package com.survey.mole.util;

import java.util.Locale;

public class StringUtils {


    public static String createExceptinMessage(Long id) {
        return String.format(Locale.ENGLISH, "Element with id %s not found", id);
    }
}
