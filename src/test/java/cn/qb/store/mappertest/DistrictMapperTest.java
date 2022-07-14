package cn.qb.store.mappertest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.qb.store.entity.District;
import cn.qb.store.mapper.DistrictMapper;

@SpringBootTest
public class DistrictMapperTest {
	
	@Autowired
	private DistrictMapper districtMapper;
	
	@Test
	public void findSubByParent() {
		List<District> districts = districtMapper.findSubByParent("100100");
		System.err.println("BEGIN");
		for (District district : districts) {
			System.err.println(district);
		}
		System.err.println("STOP");
	}
	
	@Test
	public void findOneByCode() {
		District district = districtMapper.findOneByCode("100200");
		System.err.println("BEGIN");
		System.err.println(district);
		System.err.println("STOP");
	}
}
