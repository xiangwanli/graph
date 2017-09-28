/**
 * The Train Problems
 * @Title: com.trains.graph
 * @Description: TODO
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.trains.graph.business.Route;
import com.trains.graph.business.Town;
import com.trains.graph.business.TrainGraph;
import com.trains.graph.ex.GraphException;
import com.trains.graph.ex.VertexNotFoundExeption;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @ClassName: GraphTest
 * @Description: TODO
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class GraphBuildTest extends TestCase {

	public static final String GRAPH_TEST_DATA_FILE_NAME = "Graph.txt";

	public static TrainGraph graph = null;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public GraphBuildTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(GraphBuildTest.class);
	}

	public void setUp() {
		try {
			String inputs = Utils.getStringFromStram(
					TrainProblemsApp.class.getClassLoader().getResourceAsStream(GRAPH_TEST_DATA_FILE_NAME));
			String[] data = inputs.split(",");
			HashSet<String> names = new HashSet<String>();
			List<Route> routes = new ArrayList<Route>();
			for (String item : data) {
				String expr = item.trim();
				String start = expr.substring(0, 1);
				String end = expr.substring(1, 2);
				long distance = Long.valueOf(expr.substring(2, 3));
				names.add(start);
				names.add(end);
				routes.add(new Route(start, end, distance));
			}
			List<Town> towns = names.stream().map(Town::new).collect(toList());

			// build the graph
			graph = new TrainGraph(towns, routes);
		} catch (GraphException ge) {
			fail(ge.getMessage());
		}
	}

	/**
	 * 
	 * @Description: testBuild: TODO void
	 */
	public void testVertexInGraph() {

		assertTrue(graph.getVertexKeyMap().entrySet().size() == 5);

		Arrays.asList("A", "B", "C", "D", "E").forEach(v -> assertTrue(graph.findVertexByUniqueKey(v) != null));

		Arrays.asList("X", "Y", "Z").forEach(v -> assertFalse(graph.findVertexByUniqueKey(v) != null));

	}

	/**
	 * 
	 * @Description: testEdgeData: TODO void
	 */
	public void testEdgeInGraph() {
		assertTrue(graph.getEdgeKeyMap().entrySet().size() == 9);

		Arrays.asList("A-B", "B-C", "C-D", "D-C", "D-E", "A-D", "C-E", "E-B", "A-E")
				.forEach(e -> assertTrue(graph.findEdgeByUniqueKey(e) != null));

		Arrays.asList("A-S", "B-A", "C-C", "D-A", "E-Z")
				.forEach(e -> assertFalse(graph.findEdgeByUniqueKey(e) != null));

	}

	/**
	 * 
	 * @Description: testVertexCUD: TODO void
	 */
	public void testVertexCUD() {
		try {
			String vertexKey = "TEST";
			Town town = new Town(vertexKey);
			graph.addVertex(town);
			assertTrue(graph.getVertexKeyMap().containsKey(vertexKey));
			assertTrue(graph.getVertexKeyMap().get(vertexKey).equals(town));

			graph.deleteVertex(vertexKey);
			assertFalse(graph.getVertexKeyMap().containsKey(vertexKey));

		} catch (GraphException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * 
	 * @Description: testEdgeCUD: TODO void
	 */
	public void testEdgeCUD() {
		try {
			Route route = new Route("B", "S", 999);
			graph.addEdge(route);
		} catch (GraphException e) {
			assertTrue(e.getMessage().equals((new VertexNotFoundExeption()).getMessage()));
		}

		try {
			Route route = new Route("B", "A", 999);
			graph.addEdge(route);
			assertTrue(graph.getEdgeKeyMap().containsKey("B-A"));
			assertTrue(graph.getVertexKeyMap().get("B").getEdges().contains(route));

			graph.deleteEdge(route.getUniqueKey());
			assertFalse(graph.getEdgeKeyMap().containsKey("B-A"));
			assertFalse(graph.getVertexKeyMap().get("B").getEdges().contains(route));
		} catch (GraphException e) {
			fail(e.getMessage());
		}

	}
}
