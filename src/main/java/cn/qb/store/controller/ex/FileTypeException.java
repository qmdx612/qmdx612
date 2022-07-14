package cn.qb.store.controller.ex;

/**
 * 文件上传类型异常
 * @author qinbao
 *
 */
public class FileTypeException extends FileUploadException {

	private static final long serialVersionUID = -5408030018229060338L;

	public FileTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
