package com.yunusbagriyanik.elasticsearchquerytypes.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "courses")
@Data
@Builder
public class Course {
    @Id
    private String id;
    private String courseId;
    private String name;
    private String description;
    private Instructor instructor;
    private List<Student> students;
}
