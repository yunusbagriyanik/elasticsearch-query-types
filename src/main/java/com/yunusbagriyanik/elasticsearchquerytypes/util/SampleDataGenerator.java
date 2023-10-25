package com.yunusbagriyanik.elasticsearchquerytypes.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunusbagriyanik.elasticsearchquerytypes.model.Course;
import com.yunusbagriyanik.elasticsearchquerytypes.model.Instructor;
import com.yunusbagriyanik.elasticsearchquerytypes.model.Student;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Slf4j
@RequiredArgsConstructor
@Component
public class SampleDataGenerator {
    private final RestHighLevelClient restHighLevelClient;
    private final ObjectMapper mapper = new ObjectMapper();

    //@PostConstruct
    public void generateCourses() {
        Instructor instructor1 = Instructor.builder()
                .id(UUID.randomUUID().toString())
                .instructorId("1")
                .name("Jimi Hendrix")
                .email("jimi.hendrix@example.com")
                .build();

        Instructor instructor2 = Instructor.builder()
                .id(UUID.randomUUID().toString())
                .instructorId("2")
                .name("Eric Clapton")
                .email("eric.clapton@example.com")
                .build();
        Function<Instructor, String> getInstructorId = Instructor::getId;
        addEntitiesToElasticsearch(List.of(instructor1, instructor2), IndexEnum.INSTRUCTOR.getIndexName(), getInstructorId);

        Student student1 = Student.builder()
                .id(UUID.randomUUID().toString())
                .studentId("101")
                .name("Stevie Ray Vaughan")
                .email("stevie.ray@example.com")
                .build();

        Student student2 = Student.builder()
                .id(UUID.randomUUID().toString())
                .studentId("102")
                .name("Ray Charles")
                .email("ray.charles@example.com")
                .build();
        Function<Student, String> getStudentId = Student::getId;
        addEntitiesToElasticsearch(List.of(student1, student2), IndexEnum.STUDENT.getIndexName(), getStudentId);

        Course course1 = Course.builder()
                .id(UUID.randomUUID().toString())
                .courseId("A101")
                .name("Introduction to Programming")
                .description("Learn the basics of programming")
                .instructor(instructor1)
                .students(List.of(student1, student2))
                .build();

        Course course2 = Course.builder()
                .id(UUID.randomUUID().toString())
                .courseId("B202")
                .name("Web Development")
                .description("Build interactive websites")
                .instructor(instructor2)
                .students(List.of(student2))
                .build();

        Course course3 = Course.builder()
                .id(UUID.randomUUID().toString())
                .courseId("C303")
                .name("Database Design")
                .description("Learn how to design databases")
                .instructor(instructor1)
                .students(List.of(student1))
                .build();

        Course course4 = Course.builder()
                .id(UUID.randomUUID().toString())
                .courseId("C303")
                .name("Database Design")
                .description("Léarn how to desığn databases")
                .instructor(instructor1)
                .students(List.of(student1, student2))
                .build();
        Function<Course, String> getCourseId = Course::getId;
        addEntitiesToElasticsearch(List.of(course1, course2, course3, course4), IndexEnum.COURSE.getIndexName(), getCourseId);
    }

    private <T> void addEntitiesToElasticsearch(List<T> entities, String indexName, Function<T, String> getIdFunction) {
        entities.forEach(entity -> {
            try {
                IndexRequest request = new IndexRequest();
                request.source(mapper.writeValueAsString(entity), XContentType.JSON);
                request.index(indexName);

                String id = getIdFunction.apply(entity);
                if (id != null && !id.isEmpty())
                    request.id(id);

                IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
                log.info("IndexResponse: {}", indexResponse);
            } catch (Exception e) {
                log.error("Error: ", e);
            }
        });
    }
}
