package com.yunusbagriyanik.elasticsearchquerytypes.controller;

import com.yunusbagriyanik.elasticsearchquerytypes.service.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {
    private final ElasticsearchService elasticSearchService;

    @GetMapping("/full-text-search-courses-by-desc")
    public ResponseEntity<?> searchCoursesByDesc(@RequestParam String description) {
        return ResponseEntity.ok(elasticSearchService.searchCourseByDesc(description));
    }

    @GetMapping("/fuzzy-search-courses-by-desc")
    public ResponseEntity<?> getCoursesByFuzzyDescription(@RequestParam String description) {
        return ResponseEntity.ok(elasticSearchService.getCoursesByFuzzyDescription(description));
    }

    @GetMapping("/phrase-search")
    public ResponseEntity<?> getCoursesByDescPhraseSearch(@RequestParam String description) {
        return ResponseEntity.ok(elasticSearchService.getCoursesByDescPhraseSearch(description));
    }
}
