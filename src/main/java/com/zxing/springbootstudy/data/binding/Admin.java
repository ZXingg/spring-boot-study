package com.zxing.springbootstudy.data.binding;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ZXing at 2018/3/24
 * QQ:1490570560
 */
@Data  //代替get set toString等方法
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    private String name;
    private String age;

}
