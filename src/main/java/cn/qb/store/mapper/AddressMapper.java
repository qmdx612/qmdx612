package cn.qb.store.mapper;

import java.util.List;

import cn.qb.store.entity.UserAddress;

/**
 * 处理用户地址的持久层接口
 * @author qinbao
 *
 */
public interface AddressMapper {
	
	/**
	 * 添加地址数据
	 * @param userAddress 地址数据
	 * @return 受影响的行数
	 */
	Integer addNew(UserAddress userAddress);
	
	/**
	 * 根据uid查找数据数量
	 * @param uid 用户ID
	 * @return 受影响的行数
	 */
	Integer getCountByUid(int uid);
	
	/**
	 * 根据uid查询该用户所拥有的地址数据
	 * @param uid
	 * @return
	 */
	List<UserAddress> getAddressList(int uid);
	
	/**
	 * 根据uid更改相应的默认
	 * @param uid
	 * @return
	 */
	Integer updateDefaultByUid(Integer uid);
	
	/**
	 * 根据id更改相应的默认
	 * @param id
	 * @return
	 */
	Integer updateDefaultById(Integer id);
	
	/**
	 * 根据地址信息的id查询其相关信息
	 * @param id
	 * @return 地址信息
	 */
	UserAddress findByid(Integer id);
	
	/**
	 * 根据地址id删除指定的地址信息
	 * @param id
	 * @return
	 */
	Integer deleteAddress(Integer id);
	
	/**
	 * 根据id查询最后修改时间的地址
	 * @param uid
	 * @return
	 */
	UserAddress findLastAddressByUid(Integer uid);
}
