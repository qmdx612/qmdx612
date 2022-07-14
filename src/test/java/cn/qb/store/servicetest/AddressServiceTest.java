package cn.qb.store.servicetest;


import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cn.qb.store.entity.UserAddress;
import cn.qb.store.service.AddressService;

@SpringBootTest
public class AddressServiceTest {
	@Autowired
	private AddressService addressService;
	
	@Test
	public void createNew() {
		UserAddress userAddress = new UserAddress();
		userAddress.setName("秦宝");
		userAddress.setProvince("100200");
		userAddress.setCity("100220");
		userAddress.setArea("100223");
		userAddress.setUid(1024);
		System.err.println(addressService.createNew("root", userAddress));
	}
	
	@Test
	public void findAddressList() {
		List<UserAddress> lists = addressService.findAddressList(6);
		System.err.println("BEGIN");
		for (UserAddress userAddress : lists) {
			System.err.println(userAddress);
		}
		System.err.println("STOP");
	}
	
	@Test
	public void modifyDefault() {
		Integer uid = 6;
		Integer id = 222;
		try {
			addressService.modifyDefault(uid, id);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass()+","+e.getMessage());
		}
	}
	
	@Test
	public void deleteAddress() {
		Integer uid = 6;
		Integer id = 31;
		try {
			addressService.delAddress(uid, id);
			System.err.println("OK");
		} catch (Exception e) {
			System.err.println(e.getClass()+","+e.getMessage());
		}
	}
}
