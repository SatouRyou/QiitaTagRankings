package com.qiitatagrankings.domain.service;

import com.qiitatagrankings.domain.connection.IQiitaClient;
import com.qiitatagrankings.domain.dto.ItemDto;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teradashoutarou on 2016/11/06.
 */
@Component
@Service
public class PostingService {

    @Autowired
    private IQiitaClient qiitaClient;

    public void postingTagRanking() {

        List<TagInfoDto> tagInfoDtos = qiitaClient.getTagInfoDtos();

        // タグがフォローされていなければ除外
        tagInfoDtos.removeIf(tagInfoDto -> tagInfoDto.getFollowers_count() > 0 );

        // 投稿記事がなければ除外
        tagInfoDtos.removeIf(tagInfoDto -> tagInfoDto.getItems_count() > 0 );

        // 投稿記事順にソート
        tagInfoDtos.sort((s1, s2) -> s1.getItems_count() - s2.getItems_count());

        // 投稿記事内容を記載
        StringBuilder body = new StringBuilder();

        body.append( "" );

        for ( TagInfoDto tagInfoDto : tagInfoDtos ) {

        }

        // 記事情報を生成
        ItemDto itemDto = new ItemDto();
        itemDto.setBody( body.toString() );

        // 記事を投稿する
        qiitaClient.putItem( itemDto );
    }
}
