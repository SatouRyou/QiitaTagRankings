package com.qiitatagrankings.domain.bilder;

import com.qiitatagrankings.config.ConfigReader;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import com.qiitatagrankings.domain.gateway.IQiitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by teradashoutarou on 2016/11/09.
 */
@Component
@Service
public class ItemBuilder {

    @Autowired
    private ConfigReader configReader;

    @Autowired
    private IQiitaRepository qiitaRepository;

    private final String FIRST_TITLE = "#始めに";
    private final String FIRST_MESSAGE_1 = "この記事は、Qiitaに投稿されている記事のタグ情報をランキング化したものです。";
    private final String FIRST_MESSAGE_2 = "記事が投稿されていないタグと、フォロワーがいないタグは排除されています。";
    private final String FIRST_MESSAGE_3 = "また、一万件までしかタグを取得できないため、漏れがある可能性があります。";
    private final String TAG_RANKING_TITLE = "#タグランキング";
    private final String TABLE_HEADER = "|順位|タグ名|投稿記事数|フォロワー数|前回順位|";
    private final String TABLE_LINE = "|---:|----:|-------:|--------:|-------:|";

    public String build( List<TagInfoDto> tagInfoDtos ) {

        StringBuilder item = new StringBuilder();

        item.append( FIRST_TITLE );
        item.append( "\n" );
        item.append( FIRST_MESSAGE_1 );
        item.append( "\n" );
        item.append( FIRST_MESSAGE_2 );
        item.append( "\n" );
        item.append( FIRST_MESSAGE_3 );
        item.append( "\n" );
        item.append( TAG_RANKING_TITLE );
        item.append( "\n" );
        item.append( TABLE_HEADER );
        item.append( "\n" );
        item.append( TABLE_LINE );
        item.append( "\n" );

//        Map<String, TagInfoDto> taginfoMap = qiitaRepository.getOldTag();
        Map<String, TagInfoDto> taginfoMap = new HashMap<>();
        for( TagInfoDto dto : tagInfoDtos ) {

            item.append("|");
            item.append( dto.getRank() );
            item.append("|");
            item.append( "[" + dto.getId() + "]" );
            item.append( "(http://qiita.com/tags/" + dto.getId() +")" );
            item.append("|");
            item.append( dto.getItems_count() );
            item.append("|");
            item.append( dto.getFollowers_count() );
            item.append("|");
            item.append( this.getOldRank( taginfoMap, dto.getId() ) );
            item.append("|");

            item.append( "\n" );

            // 大量のデータをプッシュすると、遅延が発生してしまうため上限を設定
            if ( dto.getRank() == this.configReader.getSettings().getView_limit() ) {
                break;
            }
        }
        return item.toString();
    }

    private String getOldRank( Map<String,TagInfoDto> info, String id ) {

        // 過去のタグ情報を取得
        TagInfoDto oldDto = info.get( id );

        if ( oldDto == null ) {
            return "-";
        }

        return String.valueOf( oldDto.getRank() );
    }
 }
