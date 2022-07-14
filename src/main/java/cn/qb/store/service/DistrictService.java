package cn.qb.store.service;

import java.util.List;

import cn.qb.store.entity.District;

/**
 * 处理省市区数据的业务层接口
 * @author qinbao
 *
 */
public interface DistrictService {
	
	/**
	 * 根据上级编码得到下级地区信息
	 * @param parent
	 * @return
	 */
	List<District> getSub(String parent);
	
	/**
	 * 根据编码得到对应地区信息
	 * @param code
	 * @return
	 */
	District getOne(String code);
}
