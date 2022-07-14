package cn.qb.store.demotest;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Demo01 {
	@Autowired
	DataSource dataSource;
	@Test
	public void test01() throws SQLException {
		System.out.println(dataSource.getConnection());
	}
}
