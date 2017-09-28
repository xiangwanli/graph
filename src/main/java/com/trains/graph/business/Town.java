/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: The Town Data Model
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.business;

import com.trains.graph.model.Vertex;

/**
 * @ClassName: Town
 * @Description: The Town Data Model
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class Town extends Vertex<Route> {

	/**
	 * @param name
	 */
	public Town(String name) {
		super(name);
	}

}
