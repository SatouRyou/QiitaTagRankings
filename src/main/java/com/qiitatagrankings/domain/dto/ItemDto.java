package com.qiitatagrankings.domain.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by teradashoutarou on 2016/11/06.
 */
@Data
public class ItemDto {

    /** 投稿記事内容 */
    private String body;
    /** タイトル */
    private String title = "デイリータグランキング";
    /** タグ */
    private List<TagDto> tags;
}
