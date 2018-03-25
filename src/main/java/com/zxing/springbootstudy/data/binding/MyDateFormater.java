//package com.zxing.springbootstudy.data.binding;
//
//import org.springframework.format.Formatter;
//import org.springframework.stereotype.Component;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Locale;
//
///**
// * Created by ZXing at 2018/3/24
// * QQ:1490570560
// */
//@Component
//public class MyDateFormater implements Formatter<Date> {
//    @Override
//    public Date parse(String s, Locale locale) throws ParseException {
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//        return format.parse(s);
//    }
//
//    @Override
//    public String print(Date date, Locale locale) {
//        return null;
//    }
//}
