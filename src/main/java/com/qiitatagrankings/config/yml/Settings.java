package com.qiitatagrankings.config.yml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.ymlのsettingsの情報を格納するクラス
 * Created by teradashoutarou on 2016/11/12.
 */
@Component
@ConfigurationProperties(prefix = "settings")
@Data
public class Settings {

    /** アクセストークン */
    private String token;
}
