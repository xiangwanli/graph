/**
 * The Train Problems
 * @Title: com.trains.graph.algorithm
 * @Description: The Graph with Direction.
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.algorithm;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.trains.graph.ex.GraphException;
import com.trains.graph.model.DirectedEdge;
import com.trains.graph.model.Graph;
import com.trains.graph.model.Vertex;

/**
 * @ClassName: DirectedGraph
 * @Description: The Graph with Direction.
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class DirectedGraph<V extends Vertex<E>, E extends DirectedEdge> extends Graph<V, E> {

	/**
	 * 
	 * Creates a new instance of DirectedGraph.
	 *
	 * @param vertexes
	 * @param edges
	 * @throws GraphException
	 */
	public DirectedGraph(List<V> vertexes, List<E> edges) throws GraphException {
		super(vertexes, edges);
	}

	/**
	 * 
	 * @Description: traversal: Starting the traversal from the root , stop when
	 *               the stop condition is matched Then the output are the route
	 *               paths according to the match conndition.
	 * @param root
	 * @param matchedConds
	 * @param stopConds
	 * @return List<DirectedPath<V,E>>
	 */
	public List<DirectedPath<V, E>> traversal(V root, Predicate<DirectedPath<V, E>> matchedConds,
			Predicate<DirectedPath<V, E>> stopConds) {
		DirectedPath<V, E> initPath = new DirectedPath<V, E>(this, root);
		return traversal(Arrays.asList(initPath), matchedConds, stopConds);
	}

	/**
	 * 
	 * @Description: traversal: The recursion method for traversal.
	 * @param visitedPaths
	 * @param matchedConds
	 * @param stopConds
	 * @return List<DirectedPath<V,E>>
	 */
	public List<DirectedPath<V, E>> traversal(List<DirectedPath<V, E>> visitedPaths,
			Predicate<DirectedPath<V, E>> matchedConds, Predicate<DirectedPath<V, E>> stopConds) {
		List<DirectedPath<V, E>> nextVisitedPaths = visitedPaths.stream().flatMap(path -> path.visit().stream())
				.collect(toList());
		List<DirectedPath<V, E>> matchedPaths = nextVisitedPaths.stream().filter(matchedConds).collect(toList());
		List<DirectedPath<V, E>> continuePaths = nextVisitedPaths.stream().filter(stopConds.negate()).collect(toList());
		if (continuePaths == null || continuePaths.size() <= 0) {
			return matchedPaths;
		} else {
			List<DirectedPath<V, E>> result = new ArrayList<DirectedPath<V, E>>();
			result.addAll(matchedPaths);
			result.addAll(traversal(continuePaths, matchedConds, stopConds));
			return result;
		}
	}

	/**
	 * 
	 * @Description: traversal: a more effective way to traversal a fixed stops.
	 * @param root
	 * @param fixedStops
	 * @return List<DirectedPath<V,E>>
	 */
	public List<DirectedPath<V, E>> traversal(V root, int fixedStops) {
		DirectedPath<V, E> initPath = new DirectedPath<V, E>(this, root);
		return traversal(Arrays.asList(initPath), fixedStops);
	}

	/**
	 * 
	 * @Description: traversal: the recursion method for the fixed stops
	 *               traversal.
	 * @param visitedPaths
	 * @param fixedStops
	 * @return List<DirectedPath<V,E>>
	 */
	public List<DirectedPath<V, E>> traversal(List<DirectedPath<V, E>> visitedPaths, int fixedStops) {
		List<DirectedPath<V, E>> nextVisitedPaths = visitedPaths.stream().flatMap(path -> path.visit().stream())
				.collect(toList());
		int leftStops = fixedStops - 1;
		if (leftStops <= 0) {
			return nextVisitedPaths;
		}
		return traversal(nextVisitedPaths, leftStops);
	}

}
