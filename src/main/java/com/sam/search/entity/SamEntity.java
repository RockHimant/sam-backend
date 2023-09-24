package com.sam.search.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SamEntity {
    private String noticeId;
    private String title;
    private String solicitationNumber;
    private String fullParentPathName;
    private String fullParentPathCode;
    private String postedDate;
    private String type;
    private String baseType;
    private String archiveType;
    private String archiveDate;
    private String typeOfSetAsideDescription;
    private String typeOfSetAside;
    private String responseDeadLine;
    private String naicsCode;
    private String naicsCodes;
    private String classificationCode;
    private String active;
    private Award award;
    private PointOfContact pointOfContact;
    private String description;
    private String organizationType;
    private OfficeAddress officeAddress;
    private PlaceOfPerformance placeOfPerformance;
    private String additionalInfoLink;
    private String uiLink;
    private Links links;
    private String resourceLinks;
    private String filename;
    private String unique_id;
    private String job_link;
    private String all_text;
    private String job_id;
}
