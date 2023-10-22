package com.yunusbagriyanik.elasticsearchquerytypes.service;

import com.yunusbagriyanik.elasticsearchquerytypes.model.Course;
import com.yunusbagriyanik.elasticsearchquerytypes.util.IndexEnum;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.Operator;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

@Service
@RequiredArgsConstructor
public class ElasticsearchService {
    private final ElasticsearchOperations elasticsearchOperations;

    public List<SearchHit<Course>> searchCourseByDesc(String searchText) {
        final NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(
                        matchQuery("description", searchText)
                                .operator(Operator.AND)
                )
                .build();
        return elasticsearchOperations.search(searchQuery, Course.class,
                IndexCoordinates.of(IndexEnum.COURSE.getIndexName())).getSearchHits();
    }
}
