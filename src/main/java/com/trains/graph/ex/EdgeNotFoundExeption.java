/**
 * The Train Problems
 * @Title: com.trains.graph.ex
 * @Description: TODO
 * @author: xiangwanli
 * @date 2017年4月10日
 * @version 1.0.0
 */
package com.trains.graph.ex;

/**
 * @ClassName: EdgeNotFoundExeption
 * @Description: The edge is not found in the graph.
 * @author xiangwanli
 * @date 2017年4月10日
 *
 */
public class EdgeNotFoundExeption extends GraphException {

	/**
	 * @Fields serialVersionUID:TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Creates a new instance of EdgeNotFoundExeption.
	 *
	 */
	public EdgeNotFoundExeption() {
		super("The edge does not exist in the graph");
	}
}
