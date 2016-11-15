package com.qiitatagrankings.domain.gateway;

/**
 * Qiita情報を保存する
 * Created by teradashoutarou on 2016/11/14.
 */
public interface IQiitaRepository {

    /**
     * 既存の情報に上書きして、タグ情報を保存する
     * @param json
     */
    public void saveQiitaTagJson( String json );

    /**
     * 保存されているタグ情報を取得する
     * @return
     */
    public String getQiitaTagJson();
}
