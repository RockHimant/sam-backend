package com.sam.search.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Award {
    private String date;
    private String number;
    private String amount;
    private Awardee awardee;
}
