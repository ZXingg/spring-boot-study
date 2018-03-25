package com.zxing.springbootstudy.data.binding;

import lombok.Data;

import java.util.Map;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
@Data
public class UserMap {
    private Map<String , User> users;
}
