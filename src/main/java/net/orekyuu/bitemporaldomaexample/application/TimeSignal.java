package net.orekyuu.bitemporaldomaexample.application;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TimeSignal {

    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
