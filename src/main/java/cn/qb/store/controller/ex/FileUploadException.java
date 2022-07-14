package cn.qb.store.controller.ex;

/**
 * 文件上传异常
 * @author qinbao
 *
 */
public class FileUploadException extends ControllerException {

	private static final long serialVersionUID = 5647227060294913963L;

	public FileUploadException() {
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
