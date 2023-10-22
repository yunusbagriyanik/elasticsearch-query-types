package com.yunusbagriyanik.elasticsearchquerytypes.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "students")
@Data
@Builder
public class Student {
    @Id
    private String id;
    private String studentId;
    private String name;
    private String email;
}
