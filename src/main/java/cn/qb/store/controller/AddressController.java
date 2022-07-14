package cn.qb.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.qb.store.entity.UserAddress;
import cn.qb.store.service.AddressService;
import cn.qb.store.util.ResponseResult;

@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {
	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/add")
	public ResponseResult<Void> HandleAdd(UserAddress userAddress,HttpSession session){
		Integer uid = getUidFromSession(session);
		userAddress.setUid(uid);
		String username = getUsernameFromSession(session);
		addressService.createNew(username, userAddress);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/list")
	public ResponseResult<List<UserAddress>> HandleList(HttpSession session){
		Integer uid = getUidFromSession(session);
		List<UserAddress> list = addressService.findAddressList(uid);
		return new ResponseResult<List<UserAddress>>(SUCCESS,list);
	}
	
	@PostMapping("/default")
	public ResponseResult<Void> handleDefault(HttpSession session,Integer id){
		Integer uid = getUidFromSession(session);
		addressService.modifyDefault(uid, id);
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/delete")
	public ResponseResult<Void> handleDelete(HttpSession session,Integer id){
		Integer uid = getUidFromSession(session);
		addressService.delAddress(uid, id);
		return new ResponseResult<Void>(SUCCESS);
	}
}
