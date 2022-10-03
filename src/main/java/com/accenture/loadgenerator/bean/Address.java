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
public class Address {
    String line1;
    String line2;
    String line3;
    String line4;
    String line5;
    String line6;
    String line7;
    String line8;
    String line9;
    String line10;

    Boolean active;
    ZonedDateTime lastActive;
}
