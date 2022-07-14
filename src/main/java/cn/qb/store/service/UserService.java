package cn.qb.store.service;

import cn.qb.store.entity.User;
import cn.qb.store.service.ex.DuplicateKeyException;
import cn.qb.store.service.ex.InsertException;
import cn.qb.store.service.ex.PasswordNotMatchException;
import cn.qb.store.service.ex.UpdateException;
import cn.qb.store.service.ex.UserNotMatchException;

/**
 * 处理用户数据的业务层接口
 * @author qinbao
 *
 */
public interface UserService {
	/**
	 * 用户注册
	 * @param 用户提交的注册信息
	 * @return 成功注册后的用户数据
	 * @throws DuplicateKeyException 重复键值异常
	 * @throws InsertException 数据库插入异常
	 */
	User reg(User user) throws DuplicateKeyException,InsertException;
	
	/**
	 * 用户登录
	 * @param username 用户提交的用户名
	 * @param password 用户提交的密码
	 * @return 成功登录的用户数据
	 * @throws UserNotMatchException 用户不存在
	 * @throws PasswordNotMatchException 密码不正确
	 */
	User login(String username,String password) throws UserNotMatchException,PasswordNotMatchException;
	
	/**
	 * 修改密码
	 * @param id 用户id
	 * @param oldPassword 原密码
	 * @param newPassword 新密码
	 * @throws UserNotMatchException 用户不存在
	 * @throws PasswordNotMatchException 密码不正确
	 * @throws UpdateException 更新数据异常
	 */
	void changePassword(Integer id,String oldPassword,String newPassword) throws UserNotMatchException,PasswordNotMatchException,UpdateException;
	
	/**
	 * 修改资料
	 * @param user 用户资料
	 * @throws UserNotMatchException
	 * @throws UpdateException
	 */
	void changeInfo(User user) throws UserNotMatchException,UpdateException;
	
	/**
	 * 修改头像
	 * @param id
	 * @param avatar
	 * @throws UserNotMatchException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer id,String avatar) throws UserNotMatchException,UpdateException;
	
	/**
	 * 根据id查询用户的基本信息
	 * @param id
	 * @return
	 * @throws UserNotMatchException
	 */
	User findUserInfo(Integer id) throws UserNotMatchException;
}
