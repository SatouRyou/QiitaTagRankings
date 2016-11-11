package com.qiitatagrankings.config;

import com.qiitatagrankings.config.yml.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 設定情報を取得するためのクラス
 * 原則このクラスを通して、データを取得すること
 * Created by teradashoutarou on 2016/11/12.
 */
@Component
@Service
public class ConfigReader {

    @Autowired
    private Settings settings;

    /**
     * Settingsの情報を取得する
     * @return
     */
    public Settings getSettings() {
        return this.settings;
    }
}
