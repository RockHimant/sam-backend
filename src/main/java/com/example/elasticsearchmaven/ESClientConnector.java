package com.example.elasticsearchmaven;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ESClientConnector {

    @Value("${elastic.search.index}")
    private String esIndex;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<Object> fetchWithMust(final String keyword) throws IOException {

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


    public List<Object> fetchToday() throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        log.info(String.format("searching for date : %s", dateStr));
        MultiMatchQuery multiMatchQueryBuilder1 = new MultiMatchQuery.Builder().fields(Arrays.asList("postedDate")).query(dateStr).build();
        MatchQuery matchQuery = new MatchQuery.Builder().field("postedDate").query(dateStr).build();

        SearchResponse<Object> employeeSearchResponse = elasticsearchClient.search(req->
                        req.index(esIndex)
                                .query(query->
                                        query.bool(bool->
                                                bool.must(must-> must.match(matchQuery)))),
                Object.class);

        return employeeSearchResponse.hits().hits().stream()
                .map(Hit::source).collect(Collectors.toList());
    }
}
