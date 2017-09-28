package com.trains.graph;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.trains.graph.business.Route;
import com.trains.graph.business.Town;
import com.trains.graph.business.TrainGraph;
import com.trains.graph.ex.GraphException;

/**
 * Hello world!
 *
 */
public class TrainProblemsApp {

	public static final String GRAPH_DATA_FILE_NAME = "Graph.txt";

	public static void main(String[] args) throws GraphException, IOException {

		// read test data
		String inputs = Utils
				.getStringFromStram(TrainProblemsApp.class.getClassLoader().getResourceAsStream(GRAPH_DATA_FILE_NAME));
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
		TrainGraph graph = new TrainGraph(towns, routes);

		// create the tasks
		int taskIndex = 1;
		List<TaskItem> taskItems = new ArrayList<TaskItem>();
		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.calcRouteDist("A-B-C"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});
		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.calcRouteDist("A-D"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});
		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.calcRouteDist("A-D-C"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.calcRouteDist("A-E-B-C-D"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.calcRouteDist("A-E-D"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.traversalMaxDepth("C", "C", 3));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.traversalExactDepth("A", "C", 4));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.traversalShortest("A", "C"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.traversalShortest("B", "B"));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		taskItems.add(new TaskItem(taskIndex++) {
			@Override
			public void run() {
				try {
					printResult(graph.traversalLimit("C", "C", 30));
				} catch (GraphException e) {
					e.printStackTrace();
				}
			}
		});

		// run the tasks , and print the result.
		taskItems.stream().forEachOrdered(item -> item.run());

	}
}
