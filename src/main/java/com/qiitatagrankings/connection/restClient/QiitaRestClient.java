package com.qiitatagrankings.connection.restClient;

import com.qiitatagrankings.domain.connection.IQiitaClient;
import com.qiitatagrankings.domain.dto.ItemDto;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by teradashoutarou on 2016/11/07.
 */
@Component
public class QiitaRestClient implements IQiitaClient {

    private RestOperations restOperations;

    public QiitaRestClient( RestTemplateBuilder restTemplateBuilder ) {
        restOperations = restTemplateBuilder.build();
    }

    /**
     * 登録されているタグ情報を10000件取得する
     * @return
     */
    public List<TagInfoDto> getTagInfoDtos() {

        List<TagInfoDto> tagInfoDtos = new ArrayList<TagInfoDto>();

        // 現状のタグの総数が不明のため、エラー対策
        try {
            // 100*100まで取得可能
            for (int i = 1; i <= 50; i++) {

                StringBuilder uri = new StringBuilder();
                uri.append("https://qiita.com/api/v2/tags?page=");
                uri.append(i);
                uri.append("&per_page=100&sort=count");
                uri.append("&token=");
                uri.append("a8b0d2bc8a6c11e5ee4fe4fd0190e2ece4952dc7");

                // 疎通
                tagInfoDtos.addAll(Arrays.asList(restOperations.getForObject(uri.toString(), TagInfoDto[].class)));
            }
        } catch ( Exception e ) {

        }
        return tagInfoDtos;
    }

    /**
     * 記事を投稿する
     * @param itemDto
     */
    public void putItem( ItemDto itemDto ) {

        RequestEntity<ItemDto> requestEntity
                = RequestEntity
                .post(URI.create("URI") )
                .contentType(MediaType.APPLICATION_JSON)
                .body( itemDto );

        // 通信実行
        restOperations.exchange( requestEntity, String.class );
    }
}
