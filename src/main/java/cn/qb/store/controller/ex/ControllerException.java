package cn.qb.store.controller.ex;

/**
 * 控制器抛出的异常基类
 * @author qinbao
 *
 */
public class ControllerException extends RuntimeException {

	private static final long serialVersionUID = -727591136736505680L;

	public ControllerException() {
		// TODO Auto-generated constructor stub
	}

	public ControllerException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ControllerException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public ControllerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ControllerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
