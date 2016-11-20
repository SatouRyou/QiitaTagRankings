package com.qiitatagrankings.infrastructure.repository;

import com.qiitatagrankings.config.ConfigReader;
import com.qiitatagrankings.domain.dto.TagInfoDto;
import com.qiitatagrankings.domain.gateway.IQiitaRepository;
import com.qiitatagrankings.domain.utils.DateUtils;
import net.arnx.jsonic.JSON;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Qiitaの情報をローカルに保存する
 * Created by teradashoutarou on 2016/11/14.
 */
@Component
public class QiitaLocalRepository implements IQiitaRepository {

    @Autowired
    private ConfigReader configReader;

    /**
     * 既存の情報に上書きして、タグ情報を保存する
     * @param json
     */
    public void saveQiitaTagJson( String json ) {

        try {
            // jsonファイル出力
            String path =
                    this.configReader.getSettings().getQiita_tagu_json() +
                            DateUtils.getNowDate( DateUtils.YYYYmmDD ) +
                            ".json";
            File file = new File( path ) ;
            FileWriter filewriter;

            filewriter = new FileWriter( file );
            BufferedWriter bw = new BufferedWriter( filewriter );
            PrintWriter pw = new PrintWriter(bw);
            pw.write( json );
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存されているタグ情報を取得する
     * @return
     */
    public String getQiitaTagJson() {

        // 読み込みファイル
        InputStream input;
        try {
            String path =
                    this.configReader.getSettings().getQiita_tagu_json() +
                            DateUtils.getYesterDay( DateUtils.YYYYmmDD ) +
                            ".json";
            input = new FileInputStream( path );
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // Json読み込み
            String json = new String(buffer);
            return json;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 保存されている過去のタグ情報を取得する
     * @return
     */
    @Override
    public Map<String, TagInfoDto> getOldTag() {

        // 過去のJSONデータをオブジェクト化する
        TagInfoDto[] tagInfoDtos = JSON.decode( this.getQiitaTagJson(), TagInfoDto[].class );

        // Stream化しラムダ式を使えるようにする
        Stream<TagInfoDto> taginfoArrayStream = Arrays.stream( tagInfoDtos );

        // 戻り値
        Map<String, TagInfoDto> res = new HashMap<>();

        // idをkey値にMap化
        taginfoArrayStream.forEach(d -> res.put(d.getId(), d));

        return res;
    }
}
