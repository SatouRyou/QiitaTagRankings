package com.qiitatagrankings.domain.dto;

import lombok.Data;

/**
 * Created by teradashoutarou on 2016/11/06.
 */
@Data
public class TagInfoDto {

    /** フォロー数 */
    private int followers_count;
    /** アイコンURL */
    private String icon_url;
    /** タグ名 */
    private String id;
    /** 投稿記事数 */
    private int items_count;
    /** 順位 */
    private int rank;
}
