package com.sam.search.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class OfficeAddress {
    private String zipcode;
    private String city;
    private String countryCode;
    private String state;
}
