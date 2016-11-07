package com.qiitatagrankings.connection.restClient;

import com.qiitatagrankings.domain.connection.IQiitaClient;
import com.qiitatagrankings.domain.dto.ItemDto;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestOperations restOperations;

    /**
     * 登録されているタグ情報を10000件取得する
     * @return
     */
    public List<TagInfoDto> getTagInfoDtos() {

        List<TagInfoDto> tagInfoDtos = new ArrayList<TagInfoDto>();

        for ( int i = 1 ; i <= 100 ; i++ ) {

            StringBuilder uri = new StringBuilder();
            uri.append( "http://qiita.com/api/v2/tags?page=" );
            uri.append( i );
            uri.append( "&per_page=100" );

            // 疎通
            tagInfoDtos.addAll( Arrays.asList(restOperations.getForObject(uri.toString(), TagInfoDto[].class) ) );
        }

        return null;
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
