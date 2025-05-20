package org.example;

import lombok.*;
import java.time.*;

@AllArgsConstructor
@Getter
@Setter
public class News {
    private final long id;
    private String title;
    private String text;
    private Instant date;
}