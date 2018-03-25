package com.zxing.springbootstudy.data.binding;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
@XmlRootElement(name = "user")
public class User {

    @NotNull
    private String name;

    @Max(200)
    private String age;

    private UserDetail detail;

    public User(@NotNull String name, @Max(200) String age) {
        this.name = name;
        this.age = age;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "age")
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @XmlElement(name = "detail")
    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", detail=" + detail +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        System.out.println(this==o);
        System.out.println(o==null);
        System.out.println(o.getClass()==this.getClass());
        System.out.println(o instanceof User);



        if (o == this) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        System.out.println(Objects.equals(age, user.age) &&
                Objects.equals(name, user.name));

        return Objects.equals(age, user.age) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
