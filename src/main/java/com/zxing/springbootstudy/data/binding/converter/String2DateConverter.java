package com.zxing.springbootstudy.data.binding.converter;

import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author zhongxing
 * @Date 2019/10/24 10:09
 * @Version 1.0
 */
@Setter
public class String2DateConverter implements Converter<String, Date> {


    /**
     * <bean id="string2DateConverter" class="com.zxing.springbootstudy.data.binding.converter.String2DateConverter"/>
     *
     * <bean id="myConversionService" class="org.springframework.context.support.ConversionServiceFactoryBean">
     * <property name="converters">
     * <set>
     * <ref bean="string2DateConverter"/>
     * </set>
     * </property>
     * </bean>
     *
     * <mvc:annotation-driven conversion-service="myConversionService"/>
     */

//    private Map<String, String> supportedDatePatterns;

    private static final Map<String, String> supportedDatePatterns = new HashMap<>(4, 1);

    static {
        supportedDatePatterns.put("^\\d{4}(-\\d{1,2}){2} +\\d{1,2}(:\\d{1,2}){2}$", "yyyy-MM-dd HH:mm:ss");
        supportedDatePatterns.put("^\\d{4}(-\\d{1,2}){2}$", "yyyy-MM-dd");
        supportedDatePatterns.put("^\\d{4}(/\\d{1,2}){2} +\\d{1,2}(:\\d{1,2}){2}$", "yyyy/MM/dd HH:mm:ss");
        supportedDatePatterns.put("^\\d{4}(/\\d{1,2}){2}$", "yyyy/MM/dd");
    }

    @SneakyThrows
    @Override
    public Date convert(String dateStr) {
        if (StringUtils.isEmpty(dateStr)) {
            return null;
        }

        Date date = null;
        for (Map.Entry<String, String> pattern : supportedDatePatterns.entrySet()) {
            if (dateStr.matches(pattern.getKey())) {
                date = new SimpleDateFormat(pattern.getValue()).parse(dateStr);
                break;
            }
        }

        return date;
    }

}


