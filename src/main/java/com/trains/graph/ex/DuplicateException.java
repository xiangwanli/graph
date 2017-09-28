/**
 * The Train Problems
 * @Title: com.trains.graph.ex
 * @Description: The Vertex or Edge does exist in the graph already.
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.ex;

/**
 * @ClassName: DuplicateException
 * @Description: The Vertex or Edge does exist in the graph already.
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class DuplicateException extends GraphException {

	/**
	 * @Feilds serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Creates a new instance of DuplicateException.
	 *
	 */
	public DuplicateException() {
		super("The Vertex or Edge does exist in the graph already.");
	}
}
