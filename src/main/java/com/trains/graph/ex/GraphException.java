/**
 * The Train Problems
 * @Title: com.trains.graph.ex
 * @Description: The Graph Exception
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.ex;

/**
 * @ClassName: GraphException
 * @Description: The Graph Exception
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class GraphException extends Exception {

	/**
	 * @Feilds serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Creates a new instance of GraphException.
	 *
	 */
	public GraphException() {
		super();
	}

	/**
	 * 
	 * Creates a new instance of GraphException.
	 *
	 * @param message
	 * @param t
	 * @param b1
	 * @param b2
	 */
	public GraphException(String message, Throwable t, boolean b1, boolean b2) {
		super(message, t, b1, b2);
	}

	/**
	 * 
	 * Creates a new instance of GraphException.
	 *
	 * @param message
	 * @param t
	 */
	public GraphException(String message, Throwable t) {
		super(message, t);
	}

	/**
	 * 
	 * Creates a new instance of GraphException.
	 *
	 * @param message
	 */
	public GraphException(String message) {
		super(message);
	}

	/**
	 * 
	 * Creates a new instance of GraphException.
	 *
	 * @param t
	 */
	public GraphException(Throwable t) {
		super(t);
	}

	
}
