/**
 * The Train Problems
 * @Title: com.trains.graph.business
 * @Description: The RoutePath for Train Graph Traversal
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.algorithm;

import java.util.List;

import com.trains.graph.business.Route;
import com.trains.graph.business.Town;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;

/**
 * @ClassName: RoutePath
 * @Description: The RoutePath for Train Graph Traversal
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class RoutePath extends DirectedPath<Town, Route> {

	/**
	 * 
	 * Creates a new instance of RoutePath.
	 *
	 * @param graph
	 * @param start
	 */
	public RoutePath(DirectedGraph<Town, Route> graph, Town start) {
		super(graph, start);
	}

	/**
	 * 
	 * Creates a new instance of RoutePath.
	 *
	 * @param path
	 * @param route
	 */
	public RoutePath(DirectedPath<Town, Route> path, Route route) {
		super(path, route);
	}

	/**
	 * 
	 * @Description: calcRoutePathDist: 
	 * 		calculate the route distance
	 * @return long
	 */
	public long calcRoutePathDist() {
		return this.getEdges().stream().mapToLong(route -> route.getDistance()).sum();
	}

	/**
	 * 
	 * @Description: visitRoute: 
	 * 		get the next visited RoutePath.
	 * @return List<RoutePath>
	 */
	public List<RoutePath> visitRoute() {
		List<RoutePath> result = this.getCurrent().getEdges().stream().map(route -> {
			return new RoutePath(this, route);
		}).collect(toList());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see com.trains.graph.algorithm.DirectedPath#toString()
	 */
	@Override
	public String toString() {
		return this.getVertexes().stream().map(Town::toString).collect(joining("-")).concat("(")
				.concat(String.valueOf(this.calcRoutePathDist())).concat(")");
	}

}
