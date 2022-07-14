package cn.qb.store.mappertest;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.qb.store.entity.User;
import cn.qb.store.mapper.UserMapper;

@SpringBootTest
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void addNew() {
		User user = new User();
		user.setUsername("qinbao");
		user.setPassword("1234");
		user.setSalt("我是盐哦");
		user.setPhone("15805698769");
		user.setEmail("1584759317@qq.com");
		System.out.println("rows:"+userMapper.addNew(user));
	}
	@Test
	public void findByUsername() {
		User user = userMapper.findByUsername("test");
		System.out.println(user);
	}
	@Test
	public void findById() {
		User user = userMapper.findById(6);
		System.out.println(user);
	}
	@Test
	public void updatePassword() {
		String password = "6699";
		Integer id = 7;
		String modifiedUser = "qinbao";
		Date modifiedTime = new Date();
		System.out.println("rows:"+userMapper.updatePassword(password, id, modifiedUser, modifiedTime));
	}
	@Test
	public void updateInfo() {
		User user = new User();
		user.setId(6);
		user.setGender(2);
		user.setPhone("rootphone");
		user.setEmail("rootemail");
		user.setModifiedUser("admin");
		user.setModifiedTime(new Date());
		System.err.println("rows:"+userMapper.updateUserInfo(user));
	}
	@Test
	public void updateAvatar() {
		String avatar = "lujing";
		Integer id = 8;
		String modifiedUser = "qinbao";
		Date modifiedTime = new Date();
		System.err.println("rows:"+userMapper.updateAvatar(id, avatar, modifiedUser, modifiedTime));
	}
}
