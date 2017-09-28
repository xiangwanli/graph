/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: The Route Data Model
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.business;

import com.trains.graph.model.DirectedEdge;

/**
 * @ClassName: Route
 * @Description: The Route Data Model
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class Route extends DirectedEdge {

	private long distance;

	/**
	 * 
	 * Creates a new instance of Route.
	 *
	 * @param fromKey
	 * @param toKey
	 * @param distance
	 */
	public Route(String fromKey, String toKey, long distance) {
		super(fromKey, toKey);
		this.distance = distance;
	}

	/**
	 * @return the distance
	 */
	public long getDistance() {
		return distance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.trains.graph.model.DirectedEdge#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer().append("{from[").append(vKey1).append("],to[").append(vKey2).append("],distance[")
				.append(this.distance).append("]}").toString();
	}
}
