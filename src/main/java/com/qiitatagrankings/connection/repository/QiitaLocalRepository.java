package com.qiitatagrankings.connection.repository;

import com.qiitatagrankings.config.ConfigReader;
import com.qiitatagrankings.domain.gateway.IQiitaRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
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
            File file = new File( this.configReader.getSettings().getQiita_tagu_json() ) ;
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
            input = new FileInputStream( this.configReader.getSettings().getQiita_tagu_json() );
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // Json読み込み
            String json = new String(buffer);
            JSONObject jsonObject = new JSONObject(json);

            // データ追加
            JSONArray jsonArray = jsonObject.getJSONArray("Employee");
            JSONObject jsonOneRecord = new JSONObject();

            for (int i = 0; i < jsonArray.length(); i++) {
                jsonOneRecord = jsonArray.getJSONObject(i);
            }

            return jsonOneRecord.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
