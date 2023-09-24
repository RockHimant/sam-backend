package com.sam.search.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlaceOfPerformance {
    private CodeName city;
    private CodeName state;
    private CodeName country;
    private String zip;
}
