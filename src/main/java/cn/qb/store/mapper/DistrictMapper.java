package cn.qb.store.mapper;

import java.util.List;

import cn.qb.store.entity.District;

/**
 * 查询省市区相关信息的持久层接口
 * @author qinbao
 *
 */
public interface DistrictMapper {
	
	/**
	 * 根据上级行政地区编码查询对应的下级行政地区信息
	 * @param parent 上级编码
	 * @return 包含行政地区的集合
	 */
	List<District> findSubByParent(String parent);
	
	/**
	 * 根据对应编码查询对应行政地区信息
	 * @param code 对应编码
	 * @return 对应地区信息
	 */
	District findOneByCode(String code);
}
