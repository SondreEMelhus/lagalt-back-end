package com.lagaltBE.lagaltBE;

import com.lagaltBE.lagaltBE.util.MyConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class LagaltBeApplication {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MyConfiguration.class);
		ctx.refresh();

		// MyBean mb1 = ctx.getBean(MyBean.class);

		// MyBean mb2 = ctx.getBean(MyBean.class);

		ctx.close();

		SpringApplication.run(LagaltBeApplication.class, args);
	}



}
