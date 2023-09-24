package com.sam.search.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Awardee {
    private String name;
    private Location location;
    private String ueiSAM;
    private String duns;
    private String cageCode;
}
