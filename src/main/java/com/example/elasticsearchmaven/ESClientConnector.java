package com.example.elasticsearchmaven;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ESClientConnector {

    @Value("${elastic.search.index}")
    private String esIndex;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<Object> fetchWithMust(String keyword) throws IOException {

        MultiMatchQuery multiMatchQueryBuilder1 = new MultiMatchQuery.Builder().fields(Arrays.asList("title^2","fullParentPathName","all_text.lws")).query(keyword).build();

        SearchResponse<Object> employeeSearchResponse = elasticsearchClient.search(req->
                        req.index(esIndex)
                                .query(query->
                                        query.bool(bool->
                                                bool.must(must-> must.multiMatch(multiMatchQueryBuilder1)))),
                Object.class);

        return employeeSearchResponse.hits().hits().stream()
                .map(Hit::source).collect(Collectors.toList());
    }
}
