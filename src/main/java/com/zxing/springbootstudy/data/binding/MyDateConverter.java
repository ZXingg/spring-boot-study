package com.zxing.springbootstudy.data.binding;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
@Component
public class MyDateConverter implements Converter<String, Date> {

    private static final List<String> formats = new ArrayList<>();
    private static final List<String> patterns = new ArrayList<>();

    static {
        formats.add("yyyy-MM");
        formats.add("yyyy-MM-dd");
        formats.add("yyyy-MM-dd HH:mm");
        formats.add("yyyy-MM-dd HH:mm:ss");
        formats.add("yyyy/MM");
        formats.add("yyyy/MM/dd");
        formats.add("yyyy/MM/dd HH:mm");
        formats.add("yyyy/MM/dd HH:mm:ss");

        patterns.add("^\\d{4}-\\d{1,2}$");
        patterns.add("^\\d{4}(-\\d{1,2}){2}$");
        patterns.add("^\\d{4}(-\\d{1,2}){2} +\\d{1,2}:\\d{1,2}$");
        patterns.add("^\\d{4}(-\\d{1,2}){2} +\\d{1,2}(:\\d{1,2}){2}$");
        patterns.add("^\\d{4}/\\d{1,2}$");
        patterns.add("^\\d{4}(/\\d{1,2}){2}$");
        patterns.add("^\\d{4}(/\\d{1,2}){2} +\\d{1,2}:\\d{1,2}$");
        patterns.add("^\\d{4}(/\\d{1,2}){2} +\\d{1,2}(:\\d{1,2}){2}$");
    }

    @Override
    public Date convert(String dateStr) {

        String value = dateStr.trim();
        if (StringUtils.isEmpty(value)) {
            return null;
        }

        for (int i = 0; i < patterns.size(); i++) {
            if (dateStr.matches(patterns.get(i))) {
                return parseDate(dateStr, formats.get(i));
            }
        }
        throw new IllegalArgumentException("日期格式不正确：" + dateStr);
    }


    /**
     * 格式化日期
     *
     * @param dateStr String 字符型日期
     * @param format  String 格式
     * @return Date 日期
     */
    private Date parseDate(String dateStr, String format) {
        Date date = null;
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            date = dateFormat.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}