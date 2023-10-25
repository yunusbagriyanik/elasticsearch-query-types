package com.yunusbagriyanik.elasticsearchquerytypes.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;

@Document(indexName = "courses")
@Setting(settingPath = "/settings.json")
@Data
@Builder
public class Course {
    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String courseId;
    @Field(type = FieldType.Text, analyzer = "elastic_analyzer")
    private String name;
    @Field(type = FieldType.Text, analyzer = "elastic_analyzer")
    private String description;
    @Field(type = FieldType.Object)
    private Instructor instructor;
    @Field(type = FieldType.Nested, includeInParent = true)
    private List<Student> students;
}
