package cn.qb.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.qb.store.entity.District;
import cn.qb.store.service.DistrictService;
import cn.qb.store.util.ResponseResult;

@RestController
@RequestMapping("/district")
public class DistrictController extends BaseController {
	
	@Autowired
	private DistrictService districtService;
	
	@RequestMapping("get")
	public ResponseResult<List<District>> getMore(@RequestParam("parent")String parent){
		List<District> districts = districtService.getSub(parent);
		return new ResponseResult<List<District>>(SUCCESS,districts);
	}
}
