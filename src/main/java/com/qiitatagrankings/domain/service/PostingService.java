package com.qiitatagrankings.domain.service;

import com.qiitatagrankings.domain.bilder.ItemBuilder;
import com.qiitatagrankings.domain.gateway.IQiitaClient;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import com.qiitatagrankings.domain.gateway.IQiitaRepository;
import net.arnx.jsonic.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by teradashoutarou on 2016/11/06.
 */
@Component
@Service
@RestController
public class PostingService {

    @Autowired
    private IQiitaClient qiitaClient;

    @Autowired
    private ItemBuilder itemBuilder;

    @Autowired
    private IQiitaRepository qiitaRepository;

    @RequestMapping( value = "/")
    public void postingTagRanking() {

        List<TagInfoDto> tagInfoDtos = qiitaClient.getTagInfoDtos();

        // タグがフォローされていなければ除外
        tagInfoDtos.removeIf(tagInfoDto -> tagInfoDto.getFollowers_count() == 0 );

        // 投稿記事がなければ除外
        tagInfoDtos.removeIf(tagInfoDto -> tagInfoDto.getItems_count() == 0 );

        // 投稿記事順にソート
        tagInfoDtos.sort((s1, s2) -> s2.getItems_count() - s1.getItems_count() );

        // 順位をセット 副作用が存在するので拡張for文で対応
        int rank = 1;
        for ( TagInfoDto tagInfoDto : tagInfoDtos ) {
            tagInfoDto.setRank( rank );
            rank++;
        }

        // 取得データを保存
        this.saveJason( tagInfoDtos );

        // 投稿記事部分を生成
        String item = itemBuilder.build( tagInfoDtos );

        // 投稿記事内容を記載
        System.out.println( item );

//        // 記事情報を生成
//        ItemDto itemDto = new ItemDto();
//        itemDto.setBody( body.toString() );

//        // 記事を投稿する
//        qiitaClient.putItem( itemDto );
    }

    public void saveJason( Object objact ) {

        String json = JSON.encode( objact );

        qiitaRepository.saveQiitaTagJson( json );
    }
}
