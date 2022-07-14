package cn.qb.store.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.qb.store.entity.User;

/**
 * 处理用户数据的持久层接口
 * @author qinbao
 *
 */
public interface UserMapper {
	/**
	 * 插入一条新的用户数据
	 * @param user
	 * @return 影响的行数
	 */
	Integer addNew(User user);
	
	/**
	 * 根据用户ID修改用户信息
	 * @param user
	 * @return
	 */
	Integer updateUserInfo(User user);
	
	/**
	 * 根据id修改用户密码，以及相关日志信息
	 * @param password
	 * @param id
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updatePassword(@Param("password")String password,@Param("id")Integer id,@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据用户id修改头像
	 * @param id 用户id
	 * @param avatar 头像路径
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateAvatar(@Param("id")Integer id,@Param("avatar")String avatar,@Param("modifiedUser")String modifiedUser,@Param("modifiedTime")Date modifiedTime);
	
	/**
	 * 根据用户名查找对应数据
	 * @param username
	 * @return 返回查询到的用户数据
	 */
	User findByUsername(String username);
	
	/**
	 * 根据用户id查找用户数据
	 * @param id
	 * @return
	 */
	User findById(Integer id);
	
}
