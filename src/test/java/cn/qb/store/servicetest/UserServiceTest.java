package cn.qb.store.servicetest;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.qb.store.entity.User;
import cn.qb.store.service.UserService;
import cn.qb.store.service.ex.ServiceException;

@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void reg() {
		User user = new User();
		user.setUsername("qinbao");
		user.setPassword("612612");
		user.setGender(1);
		user.setPhone("15805698769");
		user.setEmail("1584759317@qq.com");
		try {
		System.out.println(userService.reg(user));
		}catch(ServiceException e) {
			System.out.println(e.getClass()+":"+e.getMessage());
		}
	}
	
	@Test
	public void login() {
		String username = "qinbao";
		String password = "999999";
		try {
		System.out.println(userService.login(username,password));
		}catch(ServiceException e) {
			System.out.println(e.getClass()+":"+e.getMessage());
		}
	}
	
	@Test
	public void updatePassword() {
		Integer id = 8;
		String oldPassword = "925810";
		String newPassword = "999999";
		try {
			System.err.println("开始修改");
			userService.changePassword(id, oldPassword, newPassword);
			System.err.println("修改完成");
		}catch(ServiceException e) {
			System.err.println(e.getClass()+":"+e.getMessage());
		}
	}
	
	@Test
	public void updateInfo() {
		User user = new User();
		user.setId(6);
		user.setGender(3);
		user.setPhone("15805698739");
		user.setEmail("1584759317@qq.com");
		try {
			System.err.println("开始修改");
			userService.changeInfo(user);
			System.err.println("修改完成");
		} catch (ServiceException e) {
			System.err.println(e.getClass()+":"+e.getMessage());
		}
	}
	
	@Test
	public void updateAvatar() {
		Integer id = 2;
		String avatar = "woshilujing";
		try {
			System.err.println("开始修改");
			userService.changeAvatar(id, avatar);
			System.err.println("修改完成");
		}catch(ServiceException e) {
			System.err.println(e.getClass()+":"+e.getMessage());
		}
	}
}
