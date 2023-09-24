package com.sam.search.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Location {
     private String streetAddress;
     private CodeName city;
     private CodeName name;
     private CodeName country;
     private String zip;
}
