package cn.qb.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.qb.store.entity.User;
import cn.qb.store.mapper.UserMapper;
import cn.qb.store.service.UserService;
import cn.qb.store.service.ex.DuplicateKeyException;
import cn.qb.store.service.ex.InsertException;
import cn.qb.store.service.ex.PasswordNotMatchException;
import cn.qb.store.service.ex.UpdateException;
import cn.qb.store.service.ex.UserNotMatchException;

/**
 * 处理用户数据的业务层实现类
 * @author qinbao
 *
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		//根据用户名查询用户数据
		User data = findByUsername(user.getUsername());
		//判断是否为null
		if(data == null) {
			//若是，则对新用户数据中的密码进行加密
			//1.获取原始密码
			String oldPassword = user.getPassword();
			//2.获取随机的UUID作为盐值
			String salt = UUID.randomUUID().toString();
			//3.执行加密方法
			String newPassword =encrypt(oldPassword, salt);
			//4.为用户设置盐值与新密码
			user.setSalt(salt);
			user.setPassword(newPassword);
			//补全用户的注册数据
			Date time = new Date();
			user.setIsDelete(0);//是否已删除，0-不是，1-是
			user.setCreateUser(user.getUsername());
			user.setCreateTime(time);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(time);
			//执行注册
			addNew(user);
			return user;
		}else {
			//若否，则抛出重复异常
			throw new DuplicateKeyException("用户名已存在，请再换一个吧...");
		}
	}
	
	@Override
	public User login(String username, String password) throws UserNotMatchException, PasswordNotMatchException {
		//根据username参数查找用户数据
		User data = findByUsername(username);
		//判断是否为null
		if(data == null) {
			//若是，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
			//若否，则进一步判断是否已经删除
		}else if(data.getIsDelete() == 1) {
			//若已删除，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}else {
			//若未删除，则根据password参数和查询到的用户盐值计算加密后的真实密码
			String md5Password = encrypt(password, data.getSalt());
			//判断真实密码与查询到的用户密码是否一致
			if(data.getPassword().equals(md5Password)) {
				//若一致，则返回查询到的用户数据
				//返回前将用户盐值与密码设为null
				data.setSalt(null);
				data.setPassword(null);
				return data;
			}else {
				//若不一致，则抛出密码错误异常
				throw new PasswordNotMatchException("密码错误，请重试...");
			}
		}
	}
	
	@Override
	public void changePassword(Integer id, String oldPassword, String newPassword) throws UserNotMatchException, PasswordNotMatchException, UpdateException {
		//根据id匹配相应用户数据
		User data = findById(id);
		//判断用户是否为null
		if(data == null) {
			//若是，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}
		//若否，则判断该数据是否已删除
		if(data.getIsDelete() == 1) {
			//若已删除，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}
		//若未删除，则比较原密码是否一致
		//先取出盐值
		String salt = data.getSalt();
		//计算原md5密码
		String md5Password = encrypt(oldPassword, salt);
		if(data.getPassword().equals(md5Password)) {
			//若一致，则进行修改
			//计算新md5密码
			md5Password = encrypt(newPassword, salt);
			//执行修改操作
			updatePassword(id, md5Password, data.getUsername(),new Date());
		}else {
			//若不一致，则抛出密码错误异常
			throw new PasswordNotMatchException("原密码不正确..");
		}		
	}
	
	@Override
	public void changeInfo(User user) throws UserNotMatchException, UpdateException {
		User data = findById(user.getId());
		//判断是否为null
		if(data == null) {
			//若是，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}
		//若否，则继续判断用户是否已删除
		if(data.getIsDelete() == 1) {
			//若已删除，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}
		//若未删除，则补齐相关信息
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		//执行修改操作
		updateUserInfo(user);
	}
	
	@Override
	public User findUserInfo(Integer id) throws UserNotMatchException {
		User data = findById(id);
		if(data == null) {
			throw new UserNotMatchException("用户不存在...");
		}
		if(data.getIsDelete() == 1) {
			throw new UserNotMatchException("用户不存在...");
		}
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		return data;
	}
	
	@Override
	public void changeAvatar(Integer id, String avatar) throws UserNotMatchException, UpdateException {
		//根据id查询用户数据
		User data = findById(id);
		//判断是否为null
		if(data == null) {
			//若是，抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}
		//若否，则继续判断是否删除
		if(data.getIsDelete() == 1) {
			//若已删除，则抛出用户不存在异常
			throw new UserNotMatchException("用户不存在...");
		}
		updateAvatar(id, avatar, data.getUsername(), new Date());
	}
	
	/**
	 * 对用户密码进行加密处理
	 * @param password 用户原始密码
	 * @param salt 盐值
	 * @return 加密处理后的新密码
	 */
	private String encrypt(String password,String salt) {
		//处理
		String str = salt+password+salt;
		//循环加密
		for(int i = 0;i < 10;i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}
	
	/**
	 * 插入用户数据
	 * @param user 用户数据
	 * @throws InsertException
	 */
	private void addNew(User user) throws InsertException {
		Integer rows = userMapper.addNew(user);
		if(rows != 1) {
			throw new InsertException("添加数据出现未知错误...");
		}
	}
	
	/**
	 * 查询用户数据
	 * @param username 用户名
	 * @return 根据用户名查询到的用户数据
	 */
	private User findByUsername(String username) {
		return userMapper.findByUsername(username);
	}
	
	/**
	 * 修改用户密码即日志
	 * @param id 用户id
	 * @param password 新密码
	 * @param modifiedUser 最终修改人
	 * @param modifiedTime 最终修改时间
	 * @throws UpdateException
	 */
	private void updatePassword(Integer id,String password,String modifiedUser,Date modifiedTime) throws UpdateException {
		Integer rows = userMapper.updatePassword(password, id, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改数据出现未知错误...");
		}
	}
	
	/**
	 * 查询用户数据
	 * @param id 用户id
	 * @return 根据用户id查询到的用户数据
	 */
	private User findById(Integer id) {
		return userMapper.findById(id);
	}
	
	/**
	 * 修改用户资料
	 * @param user
	 * @throws UpdateException
	 */
	private void updateUserInfo(User user) throws UpdateException {
		Integer rows = userMapper.updateUserInfo(user);
		if(rows != 1) {
			throw new UpdateException("修改数据出现未知错误...");
		}
	}
	
	private void updateAvatar(Integer id,String avatar,String modifiedUser,Date modifiedTime) throws UpdateException {
		Integer rows = userMapper.updateAvatar(id, avatar, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改数据出现未知错误...");
		}
	}

}
