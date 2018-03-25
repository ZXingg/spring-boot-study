package com.zxing.springbootstudy.data.binding.xml;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZXing at 2018/3/25
 * QQ:1490570560
 */
public class Dom4jXmlUtils {

    public static Map<String,Object> xmlToMap (HttpServletRequest request)throws DocumentException,IOException {
       return xmlToMap(request.getInputStream());
    }

   public static Map<String,Object> xmlToMap (InputStream in)throws DocumentException,IOException {

        Map<String,Object> map=new HashMap<>();
        SAXReader reader=new SAXReader();
        Document doc=reader.read(in);

        Element root=doc.getRootElement();
        List<Element> list=root.elements();

        for(Element element:list){
            map.put(element.getName(),element.getText());
        }

        in.close();
        return map;
    }


    /**
     * bean转为xml
     */
    public static String beanToXML(Object object){
        XStream xStream=new XStream();
        xStream.alias("xml",object.getClass());//替换根节点
        return xStream.toXML(object);
    }

}
