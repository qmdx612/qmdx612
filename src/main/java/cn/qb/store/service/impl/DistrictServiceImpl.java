package cn.qb.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.qb.store.entity.District;
import cn.qb.store.mapper.DistrictMapper;
import cn.qb.store.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Override
	public List<District> getSub(String parent) {
		return findSubByParent(parent);
	}

	@Override
	public District getOne(String code) {
		return findOneByCode(code);
	}
	
	/**
	 * 根据上级行政地区编码查询对应的下级行政地区信息
	 * @param parent 上级编码
	 * @return 包含行政地区的集合
	 */
	private List<District> findSubByParent(String parent) {
		return districtMapper.findSubByParent(parent);
	}
	
	/**
	 * 根据对应编码查询对应行政地区信息
	 * @param code 对应编码
	 * @return 对应地区信息
	 */
	private District findOneByCode(String code) {
		return districtMapper.findOneByCode(code);
	}
}
