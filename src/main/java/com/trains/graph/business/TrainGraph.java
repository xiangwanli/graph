/**
 * The Train Problems
 * @Title: com.trains.graph.model.business
 * @Description: The Train Graph
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.business;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import com.trains.graph.algorithm.DirectedGraph;
import com.trains.graph.algorithm.DirectedPath;
import com.trains.graph.algorithm.RoutePath;
import com.trains.graph.ex.GraphException;
import com.trains.graph.ex.NoSuchRouteException;

/**
 * @ClassName: TrainGraph
 * @Description: The Train Graph
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class TrainGraph extends DirectedGraph<Town, Route> {

	/**
	 * the default split char for the path that user input.
	 */
	public static final String PATH_REGEX = "-";

	/**
	 * 
	 * Creates a new instance of TrainGraph.
	 *
	 * @param vertexes
	 * @param edges
	 * @throws GraphException
	 */
	public TrainGraph(List<Town> vertexes, List<Route> edges) throws GraphException {
		super(vertexes, edges);
	}

	/**
	 * 
	 * @Description: calcRouteDist: calculate the distance of the route path
	 *               that user input for example : A-B-C
	 * @param path
	 * @return
	 * @throws GraphException
	 *             String
	 */
	public String calcRouteDist(String path) throws GraphException {
		List<String> vertexKeyList = Arrays.asList(path.split(PATH_REGEX));
		return calcRouteDist(vertexKeyList);
	}

	/**
	 * 
	 * @Description: calcRouteDist: calculate the distance of the route path
	 *               that user input for example : A/B/C with split char '/'
	 * @param path
	 * @param regex
	 *            : the split char of the input path
	 * @return
	 * @throws GraphException
	 *             String
	 */
	public String calcRouteDist(String path, String regex) throws GraphException {
		List<String> vertexKeyList = Arrays.asList(path.split(regex));
		return calcRouteDist(vertexKeyList);
	}

	/**
	 * 
	 * @Description: calcRouteDist: calculate by the vertex key list.
	 * @param vertexKeyList
	 * @return : the expected message.
	 * @throws GraphException
	 *             String
	 */
	public String calcRouteDist(List<String> vertexKeyList) throws GraphException {
		String result = null;
		try {
			result = String.valueOf(calcRouteDistInternal(vertexKeyList));
		} catch (NoSuchRouteException ne) {
			result = ne.getMessage();
		}
		return result;

	}

	/**
	 * 
	 * @Description: calcRouteDistInternal: calcRouteDist: calculate by the
	 *               vertex key list.
	 * @param vertexKeyList
	 * @return : the distance
	 * @throws GraphException
	 *             long
	 */
	private long calcRouteDistInternal(List<String> vertexKeyList) throws GraphException {
		RoutePath routePath = null;
		Town lastTown = null;
		Town visitTown = null;
		for (int i = 0; i < vertexKeyList.size(); i++) {
			visitTown = this.findVertexByUniqueKey(vertexKeyList.get(i));
			if (i == 0) {
				routePath = new RoutePath(this, visitTown);
			} else {
				Route route = (Route) lastTown.visit(visitTown);
				if (route != null) {
					routePath.visit(route);
				} else {
					throw new NoSuchRouteException();
				}
			}
			lastTown = visitTown;
		}
		if (routePath == null) {
			throw new NoSuchRouteException();
		}
		return routePath.calcRoutePathDist();
	}

	/**
	 * 
	 * @Description: traversalMaxDepth: The number of the trips from starting to
	 *               ending with a maximum stop.
	 * @param startVertexKey
	 *            : the starting town
	 * @param stopVertexKey
	 *            : the ending town
	 * @param maxStops
	 *            : the maximum stops.
	 * @return
	 * @throws GraphException
	 *             String
	 */
	public String traversalMaxDepth(String startVertexKey, String stopVertexKey, int maxStops) throws GraphException {
		Town start = this.findVertexByUniqueKey(startVertexKey);
		Town stop = this.findVertexByUniqueKey(stopVertexKey);
		Predicate<DirectedPath<Town, Route>> matchedConds = path -> path.getCurrent().equals(stop);
		Predicate<DirectedPath<Town, Route>> stopConds = path -> path.getCurrent().equals(stop)
				|| path.getDepth() >= maxStops;
		List<DirectedPath<Town, Route>> result = traversal(start, matchedConds, stopConds);
		return result != null ? String.valueOf(result.size()) : "0";
	}

	/**
	 * 
	 * @Description: traversalExactDepth: THe number of trips from starting to
	 *               ending with exactly stops
	 * @param startVertexKey
	 * @param stopVertexKey
	 * @param stopDepth
	 * @return
	 * @throws GraphException
	 *             String
	 */
	public String traversalExactDepth(String startVertexKey, String stopVertexKey, int stopDepth)
			throws GraphException {
		Town start = this.findVertexByUniqueKey(startVertexKey);
		Town stop = this.findVertexByUniqueKey(stopVertexKey);
		Predicate<DirectedPath<Town, Route>> matchedConds = path -> path.getDepth() == stopDepth
				&& path.getCurrent().equals(stop);
		// Predicate<DirectedPath<Town, Route>> stopConds = path ->
		// path.getDepth() == stopDepth;
		// List<DirectedPath<Town, Route>> result = traversal(start,
		// matchedConds, stopConds);
		List<DirectedPath<Town, Route>> result = traversal(start, stopDepth);
		result = result.stream().filter(matchedConds).collect(toList());
		return result != null ? String.valueOf(result.size()) : "0";
	}

	/**
	 * 
	 * @Description: traversalShortest: The length of the shortest route from
	 *               starting to ending.
	 * @param startVertexKey
	 * @param stopVertexKey
	 * @return
	 * @throws GraphException
	 *             String
	 */
	public String traversalShortest(String startVertexKey, String stopVertexKey) throws GraphException {
		Town start = this.findVertexByUniqueKey(startVertexKey);
		Town stop = this.findVertexByUniqueKey(stopVertexKey);
		RoutePath initPath = new RoutePath(this, start);
		Predicate<RoutePath> matchedConds = routePath -> routePath.getCurrent().equals(stop);
		int edgeNums = this.getEdgeKeyMap().entrySet().size();
		Predicate<RoutePath> stopConds = routePath -> routePath.getDepth() > edgeNums;
		List<RoutePath> result = traversalTrainShortest(Arrays.asList(initPath), matchedConds, stopConds);
		return result != null && !result.isEmpty()
				? String.valueOf(result.stream().mapToLong(RoutePath::calcRoutePathDist).min().getAsLong())
				: "NO SUCH ROUTE";
	}

	/**
	 * 
	 * @Description: traversalTrainShortest: the internal recursion method for
	 *               the shortest trip.
	 * @param visitedPaths
	 * @param matchedConds
	 * @param stopConds
	 * @return List<RoutePath>
	 */
	private List<RoutePath> traversalTrainShortest(List<RoutePath> visitedPaths, Predicate<RoutePath> matchedConds,
			Predicate<RoutePath> stopConds) {
		List<RoutePath> nextVisitedPaths = visitedPaths.stream().flatMap(path -> path.visitRoute().stream())
				.collect(toList());
		List<RoutePath> matchedPaths = nextVisitedPaths.stream().filter(matchedConds).collect(toList());
		List<RoutePath> continuePaths = nextVisitedPaths.stream().filter(stopConds.negate()).collect(toList());
		if (matchedPaths != null && !matchedPaths.isEmpty()) {
			// if find a route , then add the condition (the length is less then
			// its) to the stop condition.
			long shortest = matchedPaths.stream().mapToLong(RoutePath::calcRoutePathDist).min().getAsLong();
			Predicate<RoutePath> addShortestStop = routePath -> routePath.calcRoutePathDist() >= shortest;
			addShortestStop = addShortestStop.or(stopConds);
			continuePaths = nextVisitedPaths.stream().filter(addShortestStop.negate()).collect(toList());
			List<RoutePath> result = new ArrayList<RoutePath>();
			result.addAll(matchedPaths);
			result.addAll(traversalTrainShortest(continuePaths, matchedConds, addShortestStop));
			return result;
		} else {
			if (continuePaths == null || continuePaths.size() <= 0) {
				return matchedPaths;
			} else {
				return traversalTrainShortest(continuePaths, matchedConds, stopConds);
			}
		}
	}

	/**
	 * 
	 * @Description: traversalLimit: The number of different routes from
	 *               starting to ending with a limit distance.
	 * @param startVertexKey
	 * @param stopVertexKey
	 * @param limit
	 * @return
	 * @throws GraphException
	 *             String
	 */
	public String traversalLimit(String startVertexKey, String stopVertexKey, long limit) throws GraphException {
		Town start = this.findVertexByUniqueKey(startVertexKey);
		Town stop = this.findVertexByUniqueKey(stopVertexKey);
		RoutePath initPath = new RoutePath(this, start);
		Predicate<RoutePath> matchedConds = routePath -> routePath.getCurrent().equals(stop)
				&& routePath.calcRoutePathDist() < limit;
		Predicate<RoutePath> stopConds = routePath -> routePath.calcRoutePathDist() >= limit;
		List<RoutePath> result = traversalTrain(Arrays.asList(initPath), matchedConds, stopConds);
		return result != null ? String.valueOf(result.size()) : "0";
	}

	/**
	 * 
	 * @Description: traversalTrain: breadth first search , stop the traversal
	 *               when the stop condition is matched.
	 * @param visitedPaths
	 *            : the paths that are visited already.
	 * @param matchedConds
	 *            : the output condition of the paths.
	 * @param stopConds
	 *            : the stop condition of the graph traversal.
	 * @return List<RoutePath>
	 */
	public List<RoutePath> traversalTrain(List<RoutePath> visitedPaths, Predicate<RoutePath> matchedConds,
			Predicate<RoutePath> stopConds) {
		List<RoutePath> nextVisitedPaths = visitedPaths.stream().flatMap(path -> path.visitRoute().stream())
				.collect(toList());
		List<RoutePath> matchedPaths = nextVisitedPaths.stream().filter(matchedConds).collect(toList());
		List<RoutePath> continuePaths = nextVisitedPaths.stream().filter(stopConds.negate()).collect(toList());
		if (continuePaths == null || continuePaths.size() <= 0) {
			return matchedPaths;
		} else {
			List<RoutePath> result = new ArrayList<RoutePath>();
			result.addAll(matchedPaths);
			result.addAll(traversalTrain(continuePaths, matchedConds, stopConds));
			return result;
		}
	}

}
