package com.zxing.springbootstudy.data.binding;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
public class UserDetail {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past //过去时间
    private Date birthday;

    @NotBlank
    private String address;

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }
}
