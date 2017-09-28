/**
 * The Train Problems
 * @Title: com.trains.graph.ex
 * @Description: NO SUCH ROUTE
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.ex;

/**
 * @ClassName: NoSuchRouteException
 * @Description: NO SUCH ROUTE
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class NoSuchRouteException extends GraphException {

	/**
	 * @Feilds serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Creates a new instance of NoSuchRouteException.
	 *
	 */
	public NoSuchRouteException() {
		super("NO SUCH ROUTE");
	}
}
