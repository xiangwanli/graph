/**
 * The Train Problems
 * @Title: com.trains.graph
 * @Description: TODO
 * @author: xiangwanli
 * @date 2017年4月11日
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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * @ClassName: GraphTraversalTest
 * @Description: TODO
 * @author xiangwanli
 * @date 2017年4月11日
 *
 */
public class GraphTraversalTest extends TestCase {

	public static final String GRAPH_TEST_DATA_FILE_NAME = "Graph.txt";

	public static TrainGraph graph = null;

	/**
	 * Create the test case
	 *
	 * @param testName
	 *            name of the test case
	 */
	public GraphTraversalTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(GraphTraversalTest.class);
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

	public void testCalcRouteDist() {
		try {
			assertTrue(graph.calcRouteDist("A-B-C").equals("9"));
			assertTrue(graph.calcRouteDist("A/B/C", "/").equals("9"));
			assertTrue(graph.calcRouteDist(Arrays.asList("A", "B", "C")).equals("9"));
			assertTrue(graph.calcRouteDist("A-E-B-C-D-E").equals("28"));
			assertTrue(graph.calcRouteDist("A-B-D").equals("NO SUCH ROUTE"));
		} catch (GraphException e) {
			fail(e.getMessage());
		}
	}

	public void testTraversalMaxDepth() {

		try {
			assertTrue(graph.traversalMaxDepth("C", "C", 3).equals("2"));
		} catch (GraphException e) {
			fail(e.getMessage());
		}

	}

	public void testTraversalExactDepth() {

		try {
			assertTrue(graph.traversalExactDepth("A", "C", 4).equals("3"));
		} catch (GraphException e) {
			fail(e.getMessage());
		}

	}

	public void testTraversalShortest() {

		try {
			assertTrue(graph.traversalShortest("A", "C").equals("9"));
			assertTrue(graph.traversalShortest("B", "B").equals("9"));
		} catch (GraphException e) {
			fail(e.getMessage());
		}

	}

	public void testTraversalLimit() {

		try {
			assertTrue(graph.traversalLimit("C", "C", 30).equals("7"));
		} catch (GraphException e) {
			fail(e.getMessage());
		}

	}

}
