package cn.qb.store.controller.ex;

/**
 * 文件上传过大异常
 * @author qinbao
 *
 */
public class FileLargeException extends FileUploadException {

	private static final long serialVersionUID = -8966191705514121481L;

	public FileLargeException() {
		// TODO Auto-generated constructor stub
	}

	public FileLargeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public FileLargeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public FileLargeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public FileLargeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
