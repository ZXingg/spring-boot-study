package com.zxing.springbootstudy.data.binding;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
@Data
public class UserSet {
    private Set<User> users=new HashSet<>(10);

    public UserSet(){//需要初始化数据 否则报错 不建议使用
        users.add(new User("zx","1"));
        users.add(new User("zj","2"));
    }

}
