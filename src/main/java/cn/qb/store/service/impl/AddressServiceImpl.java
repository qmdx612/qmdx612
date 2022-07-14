package cn.qb.store.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.qb.store.entity.District;
import cn.qb.store.entity.UserAddress;
import cn.qb.store.mapper.AddressMapper;
import cn.qb.store.service.AddressService;
import cn.qb.store.service.DistrictService;
import cn.qb.store.service.ex.AddressNotMatchException;
import cn.qb.store.service.ex.DeleteException;
import cn.qb.store.service.ex.IllegalDataAccessException;
import cn.qb.store.service.ex.InsertException;
import cn.qb.store.service.ex.UpdateException;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressMapper addressMapper;
	@Autowired
	private DistrictService districtService;

	@Override
	public UserAddress createNew(String username,UserAddress userAddress) throws InsertException {
		//查询该用户所拥有的的地址数量
		Integer count = getCountByUid(userAddress.getUid());
		//若为0,则是第一条地址，设为默认;若否,则非默认
		userAddress.setIsDefault(count == 0 ? 1 : 0);
		//设置district属性
		String district = getDistrict(userAddress.getProvince(), userAddress.getCity(), userAddress.getArea());
		userAddress.setDistrict(district);
		//设置日志相关信息
		Date date = new Date();
		userAddress.setCreateUser(username);
		userAddress.setCreateTime(date);
		userAddress.setModifiedUser(username);
		userAddress.setModifiedTime(date);
		addNew(userAddress);
		return userAddress;
	}
	
	@Override
	public List<UserAddress> findAddressList(int uid) {
		return getAddressList(uid);
	}
	
	@Override
	@Transactional
	public void modifyDefault(Integer uid, Integer id) throws UpdateException,AddressNotMatchException,IllegalDataAccessException {	
		UserAddress userAddress = findByid(id);
		if(userAddress == null) {
			throw new AddressNotMatchException("没有匹配的地址");
		}
		if(userAddress.getUid() != uid) {
			throw new IllegalDataAccessException("数据异常");
		}
		updateDefaultByUid(uid);
		updateDefaultById(id);
	}
	
	@Override
	@Transactional
	public void delAddress(Integer uid, Integer id)
			throws DeleteException, AddressNotMatchException, IllegalDataAccessException {
		UserAddress userAddress = findByid(id);
		if(userAddress == null) {
			throw new AddressNotMatchException("没有匹配的地址");
		}
		if(userAddress.getUid() != uid) {
			throw new IllegalDataAccessException("数据异常");
		}
		deleteAddress(id);
		if(getCountByUid(uid) != 0) {
			if(userAddress.getIsDefault() == 1) {
				userAddress = findLastAddressByUid(uid);
				modifyDefault(uid, userAddress.getId());
			}
		}
	}
	
	/**
	 * 根据省市区代号获得完整地址
	 * @param province 省号
	 * @param city 市号
	 * @param area 区号
	 * @return 完整的中文地址
	 */
	private String getDistrict(String province,String city,String area) {
		String provincename = "";
		String cityname = "";
		String areaname = "";
		District p = districtService.getOne(province);
		District c = districtService.getOne(city);
		District a = districtService.getOne(area);
		if(p != null) {
			provincename = p.getName();
		}
		if(c != null) {
			cityname = c.getName();
		}
		if(a != null) {
			areaname = a.getName();
		}
		return provincename+cityname+areaname;
	}
	
	/**
	 * 向数据库中添加一条地址数据
	 * @param userAddress
	 * @throws InsertException
	 */
	private void addNew(UserAddress userAddress) throws InsertException {
		Integer rows = addressMapper.addNew(userAddress);
		if(rows != 1) {
			throw new InsertException("添加地址时，出现未知错误，请稍后重试吧");
		}
	}
	
	/**
	 * 根据uid查询具体数量
	 * @param uid
	 * @return
	 */
	private Integer getCountByUid(int uid) {
		return addressMapper.getCountByUid(uid);
	}
	
	/**
	 * 根据uid查询地址数据
	 * @param uid
	 * @return
	 */
	private List<UserAddress> getAddressList(int uid){
		return addressMapper.getAddressList(uid);
	}
	
	/**
	 * 根据地址信息的id查询其相关信息
	 * @param id
	 * @return 地址信息
	 */
	private UserAddress findByid(Integer id) {
		return addressMapper.findByid(id);
	}
	
	/**
	 * 根据uid查询最后修改时间的地址id
	 * @param uid
	 * @return
	 */
	private UserAddress findLastAddressByUid(Integer uid) {
		return addressMapper.findLastAddressByUid(uid);
	}
	
	/**
	 * 根据uid更改相应的默认
	 * @param uid
	 * @throws UpdateException
	 */
	private void updateDefaultByUid(Integer uid) throws UpdateException {
		Integer rows = addressMapper.updateDefaultByUid(uid);
		if(rows < 1) {
			throw new UpdateException("修改默认地址时出现错误");
		}
	}
	
	private void updateDefaultById(Integer id) {
		Integer rows = addressMapper.updateDefaultById(id);
		if(rows != 1) {
			throw new UpdateException("修改默认地址时出现错误");
		}
	}
	
	/**
	 * 根据地址id删除指定的地址信息
	 * @param id
	 */
	private void deleteAddress(Integer id) {
		Integer rows = addressMapper.deleteAddress(id);
		if(rows != 1) {
			throw new DeleteException("删除地址时出现错误");
		}
	}
}
