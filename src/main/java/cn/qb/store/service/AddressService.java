package cn.qb.store.service;

import java.util.List;

import cn.qb.store.entity.UserAddress;
import cn.qb.store.service.ex.AddressNotMatchException;
import cn.qb.store.service.ex.DeleteException;
import cn.qb.store.service.ex.IllegalDataAccessException;
import cn.qb.store.service.ex.InsertException;
import cn.qb.store.service.ex.UpdateException;

/**
 * 处理地址数据的业务层接口
 * @author qinbao
 *
 */
public interface AddressService {
	
	/**
	 * 添加新的地址数据
	 * @param userAddress 地址信息
	 * @throws InsertException 插入异常
	 */
	UserAddress createNew(String username,UserAddress userAddress) throws InsertException;
	
	/**
	 * 根据uid查询该用户所拥有的地址数据
	 * @param uid
	 * @return
	 */
	List<UserAddress> findAddressList(int uid);
	
	/**
	 * 更改默认地址
	 * @param uid 将uid全部设为非默认
	 * @param id 将需要的id设为默认
	 * @throws UpdateException
	 * @throws AddressNotMatchException
	 * @throws IllegalDataAccessException
	 */
	void modifyDefault(Integer uid,Integer id) throws UpdateException,AddressNotMatchException,IllegalDataAccessException;
	
	/**
	 * 删除指定地址
	 * @param uid 用于验证数据是否异常
	 * @param id 指定地址的id
	 * @throws DeleteException
	 * @throws AddressNotMatchException
	 * @throws IllegalDataAccessException
	 */
	void delAddress(Integer uid,Integer id)throws DeleteException,AddressNotMatchException,IllegalDataAccessException;
}
