package com.qiitatagrankings.domain.connection;

import com.qiitatagrankings.domain.dto.ItemDto;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by teradashoutarou on 2016/11/06.
 */
@Service
public interface IQiitaClient {

    /**
     * 登録されているタグ情報を10000件取得する
     * @return
     */
    public List<TagInfoDto> getTagInfoDtos();

    /**
     * 記事を投稿する
     * @param itemDto
     */
    public void putItem( ItemDto itemDto);
}
