package com.accenture.loadgenerator.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Name {
    String firstName;
    String lastName;
    Boolean active;
    ZonedDateTime lastActive;
}
