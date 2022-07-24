package org.grube.registrationloginjwtreact.registration;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        // todo: use regex or spring's internal validator instead
        return s.contains("@");
    }
}
