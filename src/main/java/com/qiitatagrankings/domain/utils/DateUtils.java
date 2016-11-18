package com.qiitatagrankings.domain.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日付関連ユーティリティクラス
 * Created by teradashoutarou on 2016/11/18.
 */
public class DateUtils {

    public static final String YYYYmmDD = "YYYYmmDD";

    /**
     * 指定された現在日付の文字列を返却する
     * @param format
     * @return
     */
    public static String getNowDate( String format ) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( format );
        return simpleDateFormat.format( new Date() );
    }

    /**
     * 指定された現在日付の文字列を返却する
     * @param format
     * @return
     */
    public static String getYesterDay( String format ) {
        Calendar cal = Calendar.getInstance();
        cal.setTime( new Date() );
        cal.add(Calendar.DAY_OF_MONTH, -1);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( format );
        return simpleDateFormat.format( cal.getTime() );
    }
}
