package com.yunusbagriyanik.elasticsearchquerytypes.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "instructors")
@Data
@Builder
public class Instructor {
    @Id
    private String id;
    private String instructorId;
    private String name;
    private String email;
}
