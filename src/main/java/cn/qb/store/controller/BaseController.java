package cn.qb.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.qb.store.controller.ex.ControllerException;
import cn.qb.store.controller.ex.FileEmptyException;
import cn.qb.store.controller.ex.FileLargeException;
import cn.qb.store.controller.ex.FileTypeException;
import cn.qb.store.controller.ex.FileUploadException;
import cn.qb.store.service.ex.AddressNotMatchException;
import cn.qb.store.service.ex.DuplicateKeyException;
import cn.qb.store.service.ex.IllegalDataAccessException;
import cn.qb.store.service.ex.InsertException;
import cn.qb.store.service.ex.PasswordNotMatchException;
import cn.qb.store.service.ex.ServiceException;
import cn.qb.store.service.ex.UpdateException;
import cn.qb.store.service.ex.UserNotMatchException;
import cn.qb.store.util.ResponseResult;

/**
 * 统一处理异常的控制器基类并提供统一资源
 * @author qinbao
 *
 */
public abstract class BaseController {
	
	protected static final Integer SUCCESS = 1;
	
	@ExceptionHandler({ServiceException.class,ControllerException.class})
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e){
		Integer excode = 0;
		if(e instanceof DuplicateKeyException) {
			//违反唯一约束-2
			excode = 2;
		} else if(e instanceof InsertException) {
			//数据库插入异常-3
			excode = 3;
		} else if(e instanceof UserNotMatchException) {
			//用户不存在-4
			excode = 4;
		} else if(e instanceof PasswordNotMatchException) {
			//密码不正确-5
			excode = 5;
		} else if(e instanceof UpdateException) {
			//数据库更新异常-6
			excode = 6;
		}else if(e instanceof FileEmptyException) {
			//文件为空异常-7
			excode = 7;
		}else if(e instanceof FileLargeException) {
			//文件上传过大-8
			excode = 8;
		}else if(e instanceof FileTypeException) {
			//文件类型异常-9
			excode = 9;
		}else if(e instanceof FileUploadException) {
			//文件上传异常-10
			excode = 10;
		}else if(e instanceof AddressNotMatchException) {
			//地址不匹配-11
			excode = 11;
		}else if(e instanceof IllegalDataAccessException) {
			//数据异常-12
			excode = 12;
		}else if(e instanceof AddressNotMatchException) {
			//地址不匹配-13
			excode = 13;
		}
		return new ResponseResult<Void>(excode,e);
	}
	
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
	
	protected String getUsernameFromSession(HttpSession session) {
		return session.getAttribute("username").toString();
	}
}
