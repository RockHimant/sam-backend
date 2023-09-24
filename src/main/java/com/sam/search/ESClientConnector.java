package com.sam.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.sam.search.entity.Result;
import com.sam.search.entity.SamEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ESClientConnector {

    @Value("${elastic.search.index}")
    private String esIndex;
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public List<Result> fetchWithMust(final String keyword) throws IOException {

        MultiMatchQuery multiMatchQueryBuilder1 = new MultiMatchQuery.Builder().fields(Arrays.asList("title^2","fullParentPathName","all_text.lws", "naicsCode", "archiveType")).query(keyword).build();

        SearchResponse<SamEntity> employeeSearchResponse = elasticsearchClient.search(req->
                        req.index(esIndex)
                                .query(query->
                                        query.bool(bool->
                                                bool.must(must-> must.multiMatch(multiMatchQueryBuilder1)))),
                SamEntity.class);

        return employeeSearchResponse.hits().hits().stream()
                .map(new Function<Hit<SamEntity>, Result>() {
                    @Override
                    public Result apply(Hit<SamEntity> objectHit) {
                        return new Result( objectHit.source(), objectHit.score());
                    }
                })
                .collect(Collectors.toList());
    }


    public List<Result> fetchToday() throws IOException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        log.info(String.format("searching for date : %s", dateStr));
        MultiMatchQuery multiMatchQueryBuilder1 = new MultiMatchQuery.Builder().fields(Arrays.asList("postedDate")).query(dateStr).build();
        MatchQuery matchQuery = new MatchQuery.Builder().field("postedDate").query(dateStr).build();

        SearchResponse<SamEntity> employeeSearchResponse = elasticsearchClient.search(req->
                        req.index(esIndex)
                                .query(query->
                                        query.bool(bool->
                                                bool.must(must-> must.match(matchQuery)))),
                SamEntity.class);

        return employeeSearchResponse.hits().hits().stream()
                .map(new Function<Hit<SamEntity>, Result>() {
                    @Override
                    public Result apply(Hit<SamEntity> objectHit) {
                        return new Result(objectHit.source(), objectHit.score());
                    }
                }).collect(Collectors.toList());
    }

    public List<Result> searchExact(final String keyword) throws IOException {
        List<Result> sams = fetchWithMust(keyword);

        Predicate<Result> titlePredicate = entity -> entity.getData().getTitle().toLowerCase().contains(keyword.toLowerCase());
        //Predicate<SamEntity> titlePredicate = entity -> entity.getTitle().contains(keyword);

        if (!CollectionUtils.isEmpty(sams)) {
            return sams.stream().filter(titlePredicate).collect(Collectors.toList());
        }

        return Collections.EMPTY_LIST;
    }
}
