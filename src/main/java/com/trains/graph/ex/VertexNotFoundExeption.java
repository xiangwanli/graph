/**
 * The Train Problems
 * @Title: com.trains.graph.ex
 * @Description: The vertex does not exist in the graph
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.ex;

/**
 * @ClassName: VertexNotExsitException
 * @Description: The vertex does not exist in the graph
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class VertexNotFoundExeption extends GraphException {

	/**
	 * @Feilds serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * Creates a new instance of VertexNotFoundExeption.
	 *
	 */
	public VertexNotFoundExeption() {
		super("The vertex does not exist in the graph");
	}

}
