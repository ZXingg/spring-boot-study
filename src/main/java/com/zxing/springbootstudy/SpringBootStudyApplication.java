package com.zxing.springbootstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class SpringBootStudyApplication {

	//设置时区 相差8小时 已在配置文件中设置GMT+8
//	@PostConstruct
//	void started() {
//		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
//	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStudyApplication.class, args);
	}
}
