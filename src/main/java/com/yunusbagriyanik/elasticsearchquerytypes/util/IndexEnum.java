package com.yunusbagriyanik.elasticsearchquerytypes.util;

public enum IndexEnum {
    COURSE("courses"),
    INSTRUCTOR("instructors"),
    STUDENT("students");

    public final String indexName;

    IndexEnum(String indexName) {
        this.indexName = indexName;
    }

    public String getIndexName() {
        return indexName;
    }
}
