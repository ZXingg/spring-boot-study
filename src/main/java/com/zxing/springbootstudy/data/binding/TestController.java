package com.zxing.springbootstudy.data.binding;

import com.sun.org.apache.xerces.internal.impl.XMLStreamReaderImpl;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import javax.xml.stream.XMLStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
@RestController
@RequestMapping("/data")
public class TestController {

    //    http://localhost:8060/data/int?age=10
    @RequestMapping("/int")
    public String testInt(int age) {//基本类型参数必传  封装类型可以为空
        return "age: " + age;
    }

    //    http://localhost:8060/data/int-default?age=10
    @RequestMapping("/int-default")
    public String testInt2(@RequestParam(value = "age", required = false, defaultValue = "0") int age) {//设置为非必须参数
        return "age: " + age;
    }

    //    http://localhost:8060/data/integer?age=1
    @RequestMapping("/integer")
    public String testInteger(Integer age) {//基本类型参数必传  封装类型可以为空
        return "age: " + age;
    }

    //    http://localhost:8060/data/integer/1
    @RequestMapping("/integer-path/{age}")//TODO 设置默认值
    public String testInteger2(@PathVariable(value = "age", required = false) Integer age) {//在uri中带参
        return "age: " + age;
    }

    //数组
//    http://localhost:8060/data/array?name=zx&name=zjw&name=lwh
    @GetMapping("/array")
    public String arrey(String[] name) {
        return Arrays.asList(name).toString();
    }

    //对象
    //    http://localhost:8060/data/object?name=zx&age=01&detail.address=jx&detail.birthday=2018/10/11  //日期默认格式
    //    http://localhost:8060/data/object?name=zx&age=01&detail.address=jx&detail.birthday=2018-10-11  //加DateTimeFormat注解后
    //@PostMapping("/object")
    @GetMapping("/object")
    public User object(User user) {
        System.out.println(user.getName() + '\n'
                + user.getAge() + '\n'
                + user.getDetail().getBirthday() + '\n'
                + user.getDetail().getAddress());
        return user;
    }

    //多对像同属性
    //http://localhost:8060/data/object2?user.name=zx&admin.name=lwh&age=01&detail.birthday=2018-10-11&detail.address=jx
    @GetMapping("/object2")
    public String object2(User user, Admin admin) {
        return user + "\n" + admin;
    }

    //不加此配置 直接使用user.name无效
    @InitBinder("user")
    public void initUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }

    @InitBinder("admin")
    public void initAdmin(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("admin.");
    }

    //List
    @GetMapping("/list")
    public List<User> list(ArrayList<User> users) { //错误使用 List中没有默认构造函数 报错
        // ArrayList中没有对应的get set方法所以返回[]
        return users;
    }

    //http://localhost:8060/data/list2?users[0].name=zx&users[20].name=ll   //将生成size为21列表
    @GetMapping("/list2")
    public List<User> list2(UserList users) {
        System.out.println(users.getUsers().size());
        return users.getUsers();
    }

    //Set  //需初始化Set数据  可选 重写hash equals方法 不建议使用
    //http://localhost:8060/data/set?users[0].name=zx&users[1].name=ll&users[1].age=3
    @GetMapping("/set")
    public Set<User> set(UserSet users) {
        System.out.println(users.getUsers().size());
        return users.getUsers();
    }

    //Map
    //http://localhost:8060/data/map?users[%27x%27].name=zx&users[%27y%27].name=ll&users[%27z%27].age=3
    @GetMapping("/map")
    public Map<String, User> map(UserMap users) {
        return users.getUsers();
    }

    //Json
    /*
    {
        "name": "zx",
        "age": "3",
        "detail": {
            "birthday": "2018-11-11T00:00:00.000+0000",
            "address": "jx"
        }
    }
    * */
    @PostMapping("/json")
    public User json(@RequestBody User user) {
        return user;
    }

    //Xml
    //<user>
    //	<name>zx</name>
    //	<age>3</age>
    //	<detail>
    //		<address>jx</address>
    //	</detail>
    //</user>
    //方式1; 在对象中使用Xml注解配置
    //方式2： 使用dom4j解析xml
    @PostMapping("/xml")
    public User xml(@RequestBody User user) {
        return user;
    }

    //数据格式转换
    //
    @GetMapping("/date")
    public Date date(Date date) {
        return date;
    }

//    @InitBinder("date")//局部有效 仅本类有效
//    public void initDate(WebDataBinder binder){
//        binder.registerCustomEditor(Date.class,new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"),true));
//    }


}
