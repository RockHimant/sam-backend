package com.sam.search.entity;

import com.sam.search.entity.SamEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {
    private SamEntity data;
    private double score;
}
