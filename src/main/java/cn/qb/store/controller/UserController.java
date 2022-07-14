package cn.qb.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.qb.store.controller.ex.FileEmptyException;
import cn.qb.store.controller.ex.FileLargeException;
import cn.qb.store.controller.ex.FileTypeException;
import cn.qb.store.controller.ex.FileUploadException;
import cn.qb.store.entity.User;
import cn.qb.store.service.UserService;
import cn.qb.store.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;
	
	/**
	 * 允许上传的文件类型
	 */
	private static final List<String> FILE_TYPE = new ArrayList<String>();
	
	/**
	 * 初始化文件类型集合
	 */
	static {
		FILE_TYPE.add("image/jpeg");
		FILE_TYPE.add("image/png");
	}
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user){
		//执行注册
		userService.reg(user);
		//返回结果
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/login.do")
	public ResponseResult<User> handleLogin(String username,String password,HttpSession session){
		//执行登录
		User data = userService.login(username, password);
		//将用户的id,用户名等关键信息存入session
		session.setAttribute("uid", data.getId());
		session.setAttribute("username", data.getUsername());
		//返回结果
		return new ResponseResult<User>(SUCCESS,data);
	}
	
	@PostMapping("/upassword.do")
	public ResponseResult<Void> handleUpdatePassword(@RequestParam("old_password") String oldPassword,@RequestParam("new_password") String newPassword,HttpSession session){
		//获得绑定在session上的用户id
		Integer id = getUidFromSession(session);
		//修改密码
		userService.changePassword(id, oldPassword, newPassword);
		//返回结果
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@RequestMapping("/info.do")
	public ResponseResult<User> getInfo(HttpSession session){
		User user = userService.findUserInfo(getUidFromSession(session));
		return new ResponseResult<User>(SUCCESS,user);
	}
	
	@PostMapping("/uinfo.do")
	public ResponseResult<Void> handleUpdateInfo(User user,HttpSession session){
		//执行修改
		user.setId(getUidFromSession(session));
		userService.changeInfo(user);
		//返回结果
		return new ResponseResult<Void>(SUCCESS);
	}
	
	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpdateAvatar(@RequestParam("file")MultipartFile file,HttpSession session){
		// 判断文件是否为空
		if(file.isEmpty()) {
			//抛出文件为空异常
			throw new FileEmptyException("头像不能为空");
		}
		// 判断文件大小
		if(file.getSize() > 5 * 1024 * 1024) {
			//抛出文件大小异常
			throw new FileLargeException("选择的文件太大啦");
		}
		// 判断文件类型
		if(!FILE_TYPE.contains(file.getContentType())) {
			//抛出文件类型异常
			throw new FileTypeException("文件类型不匹配");
		}
		//获得存储头像的文件夹路径
		String parentPath = session.getServletContext().getRealPath("upload_avatar");
		File parent = new File(parentPath);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		//获得头像存储的文件名
		String originalName = file.getOriginalFilename();
		String fileNmae = System.currentTimeMillis() + "" + (new Random().nextInt(90000000) + 10000000) + originalName.substring(originalName.lastIndexOf("."));
		//确定文件
		File dest = new File(parent,fileNmae);
		//保存上传的文件
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			//抛出异常：文件上传异常
			throw new FileUploadException("上传失败，稍后重试...");
		} catch (IOException e) {
			e.printStackTrace();
			//抛出异常：文件上传异常
			throw new FileUploadException("上传失败，稍后重试...");
		}
		//修改头像
		String avatar = "/upload_avatar/" + fileNmae;
		userService.changeAvatar(getUidFromSession(session), avatar);
		//返回结果，包含新头像
		ResponseResult<String> res = new ResponseResult<String>(SUCCESS);
		res.setData(avatar);
		return res;
	}
}
