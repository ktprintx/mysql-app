package com.printix.mysqlapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;


@SpringBootTest
@ActiveProfiles("test")
class MysqlAppApplicationTests {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		assertThat(context).isNotNull();
		// Example to check if a specific bean is loaded
		assertThat(context.containsBean("MySQLService")).isTrue();
	}

}
