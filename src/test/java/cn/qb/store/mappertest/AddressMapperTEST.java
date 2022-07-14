package cn.qb.store.mappertest;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.qb.store.entity.UserAddress;
import cn.qb.store.mapper.AddressMapper;

@SpringBootTest
public class AddressMapperTEST {
	@Autowired
	private AddressMapper addressMapper;
	
	@Test
	public void addNew() {
		UserAddress userAddress = new UserAddress();
		userAddress.setName("秦宝宝");
		userAddress.setUid(2);
		userAddress.setCity("合肥");
		Integer rows = 0;
		try {
			rows = addressMapper.addNew(userAddress);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rows:"+rows);
	}
	
	@Test
	public void getCountByUid() {
		int uid = 2;
		Integer rows = 0;
		try {
			rows = addressMapper.getCountByUid(uid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("rows:"+rows);
	}
	
	@Test
	public void getAddressList() {
		List<UserAddress> lists = addressMapper.getAddressList(8);
		for (UserAddress userAddress : lists) {
			System.out.println(userAddress);
		}
	}
	
	@Test
	public void updateDefaultByUid() {
		Integer uid = 6;
		System.err.println(addressMapper.updateDefaultByUid(uid));
	}
	
	@Test
	public void updateDefaultById() {
		Integer id = 19;
		System.err.println(addressMapper.updateDefaultById(id));
	}
	
	@Test
	public void findById() {
		Integer id = 19;
		System.err.println(addressMapper.findByid(id));
	}
	
	@Test
	public void deleteAddress() {
		Integer id = 29;
		System.err.println(addressMapper.deleteAddress(id));
	}
	
	@Test
	public void findLast() {
		Integer uid = 6;
		System.err.println(addressMapper.findLastAddressByUid(uid));
	}
}
