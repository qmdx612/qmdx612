package cn.qb.store.service.ex;

/**
 * 违反约束异常
 * @author qinbao
 *
 */
public class DuplicateKeyException extends ServiceException {

	private static final long serialVersionUID = -365382177550216434L;

	public DuplicateKeyException() {
		// TODO Auto-generated constructor stub
	}

	public DuplicateKeyException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DuplicateKeyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateKeyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

}
