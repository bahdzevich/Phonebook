package com.bogdevich.profile.controller.util.viewresolver;

import com.bogdevich.profile.controller.util.view.CsvView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class CsvViewResolver implements ViewResolver {
    @Override
    public View resolveViewName(String s, Locale locale) throws Exception {
        return new CsvView();
    }
}
