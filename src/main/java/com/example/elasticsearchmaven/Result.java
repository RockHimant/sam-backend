package com.example.elasticsearchmaven;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Result {
     private String title;
     private String fullParentPathName;
     private String postedDate;
     private String resourceLinks;
     private String unique_id;
     private String job_link;
     private String file_name;
     private String job_id;
     private String noticeId;
}
