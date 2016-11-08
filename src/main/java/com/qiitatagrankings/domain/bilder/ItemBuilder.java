package com.qiitatagrankings.domain.bilder;

import com.qiitatagrankings.domain.dto.TagInfoDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teradashoutarou on 2016/11/09.
 */
@Component
@Service
public class ItemBuilder {

    private final String FIRST_TITLE = "#始めに";
    private final String FIRST_MESSAGE_1 = "この記事は、Qiitaに投稿されている記事のタグ情報をランキング化したものです。";
    private final String FIRST_MESSAGE_2 = "記事が投稿されていないタグと、フォロワーがいないタグは排除されています。";
    private final String FIRST_MESSAGE_3 = "また、一万件までしかタグを取得できないため、漏れがある可能性があります。";
    private final String TAG_RANKING_TITLE = "#タグランキング";
    private final String TABLE_HEADER = "|順位|タグ名|投稿記事数|フォロワー数|";
    private final String TABLE_LINE = "|---:|----:|-------:|--------:|";

    public String build( List<TagInfoDto> tagInfoDtos ) {

        StringBuilder item = new StringBuilder();

        item.append( FIRST_TITLE );
        item.append( "<br>" );
        item.append( FIRST_MESSAGE_1 );
        item.append( "<br>" );
        item.append( FIRST_MESSAGE_2 );
        item.append( "<br>" );
        item.append( FIRST_MESSAGE_3 );
        item.append( "<br>" );
        item.append( TAG_RANKING_TITLE );
        item.append( "<br>" );
        item.append( TABLE_HEADER );
        item.append( "<br>" );
        item.append( TABLE_LINE );
        item.append( "<br>" );

        int count = 1;
        for( TagInfoDto dto : tagInfoDtos ) {

            item.append("|");
            item.append( count );
            item.append("|");
            item.append( "[" + dto.getId() + "]" );
            item.append( "(http://qiita.com/tags/" + dto.getId() +")" );
            item.append("|");
            item.append( dto.getItems_count() );
            item.append("|");
            item.append( dto.getFollowers_count() );
            item.append("|");

            item.append( "<br>" );

            count++;

        }

        return item.toString();
    }
}
