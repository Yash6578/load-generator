package com.accenture.loadgenerator.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    String type;
    String id;
    ZonedDateTime eventTime;
    List<Name> nameList;
    List<Address> addressList;
}
