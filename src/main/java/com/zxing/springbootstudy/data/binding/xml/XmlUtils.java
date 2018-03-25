package com.zxing.springbootstudy.data.binding.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by ZXing at //
 * QQ:
 */
public class XmlUtils {
    /**
     * java对象转换为xml文件
     *
     * @param bean java对象
     * @param load xml格式对象
     * @return
     * @throws JAXBException
     */
    public static String beanToXml(Object bean, Class<?> load) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(load);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(bean, writer);

        return writer.toString();
    }

    /**
     * 将xml转换为java对象
     * @param xmlPath
     * @param load
     * @return
     * @throws JAXBException
     * @throws IOException
     */
    public static Object xmlToBean(String xmlPath, Class<?> load) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(load);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Object object = unmarshaller.unmarshal(new File(xmlPath));

        return object;
    }

}
