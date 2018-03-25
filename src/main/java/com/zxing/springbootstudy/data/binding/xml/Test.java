package com.zxing.springbootstudy.data.binding.xml;

import com.zxing.springbootstudy.data.binding.Admin;
import org.dom4j.DocumentException;
import org.springframework.core.io.Resource;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ZXing at //
 * QQ:
 */
public class Test {


    //写入到xml文件中
    //String xmlPath = System.getProperty("user.dir");
    private static final String xmlPath = new File("src\\main\\java\\com\\zxing\\springbootstudy\\data\\binding\\xml\\student.xml").getAbsolutePath();

    public static void main(String[] args) {
//        testBean2Xml();
//        testXml2Bean();
//        testDom4jBean2Xml();
        testDom4jXml2Map();
    }

    public static void testBean2Xml() {

        String xmlStr = null;
        try {
            xmlStr = XmlUtils.beanToXml(getStudentList(), StudentList.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        BufferedWriter bfw = null;
        try {
            bfw = new BufferedWriter(new FileWriter(new File(xmlPath)));
            bfw.write(xmlStr);
            bfw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void testXml2Bean() {
        Object object = null;
        try {
            object = XmlUtils.xmlToBean(xmlPath, StudentList.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StudentList students = (StudentList) object;
        List<Student> studentList = students.getStudents();

        for (int i = 0; i < studentList.size(); i++) {
            System.out.println(studentList.get(i).name);
            System.out.println(studentList.get(i).sex);
            System.out.println(studentList.get(i).number);
            System.out.println(studentList.get(i).className);
            for (String str : studentList.get(i).hobby) {
                System.out.print(str + " ");
            }
            System.out.println("\n-------------");
        }
    }

    public static void testDom4jBean2Xml() {
        System.out.println(Dom4jXmlUtils.beanToXML(new Admin("小兴", "13")));
        //System.out.println(Dom4jXmlUtils.beanToXML(getStudentList()));
    }

    public static void testDom4jXml2Map() {
        try {
            //Map map=Dom4jXmlUtils.xmlToMap(new FileInputStream(xmlPath));
            ByteArrayInputStream inputStream = new ByteArrayInputStream(
                    Dom4jXmlUtils.beanToXML(new Admin("小兴", "13"))
                            .getBytes());
            Map map = Dom4jXmlUtils.xmlToMap(inputStream);

            for (Object obj : map.keySet()) {
                System.out.printf("%s-->%s\n", (String) obj, map.get(obj));
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudentList getStudentList() {
        List<String> hobby = new ArrayList<>();
        hobby.add("篮球");
        hobby.add("音乐");
        hobby.add("乒乓球");

        List<Student> studentList = new ArrayList<>();

        Student st = new Student("张三", "男", 1, "尖子班", hobby);
        studentList.add(st);
        Student st1 = new Student("李四", "男", 2, "普通班", hobby);
        studentList.add(st1);
        Student st2 = new Student("莉莉", "女", 3, "普通班", hobby);
        studentList.add(st2);

        StudentList students = new StudentList();
        students.setStudents(studentList);

        return students;
    }
}
