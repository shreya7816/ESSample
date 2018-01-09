package com.java.elasticsearch.entity;

public enum SortDirection {

    ASC {
        @Override
        public String toString() {
            return "asc";
        }
    },
    DESC {
        @Override
        public String toString() {
            return "desc";
        }
    }
}
