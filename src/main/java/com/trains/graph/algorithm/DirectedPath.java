/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: The Path with Direction.
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.algorithm;

import java.util.LinkedList;
import java.util.List;

import com.trains.graph.ex.GraphException;
import com.trains.graph.model.DirectedEdge;
import com.trains.graph.model.Vertex;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;

/**
 * 
 * @ClassName: DirectedPath
 * @Description: The Path with Direction.
 * @author xiangwanli
 * @date 2017年4月10日
 *
 * @param <V>
 * @param <E>
 */
public class DirectedPath<V extends Vertex<E>, E extends DirectedEdge> {

	private DirectedGraph<V, E> graph;

	private List<V> vertexes = new LinkedList<V>();

	private List<E> edges = new LinkedList<E>();

	private V current;

	private int depth;

	/**
	 * 
	 * Creates a new instance of DirectedPath.
	 *
	 * @param graph
	 * @param start
	 */
	public DirectedPath(DirectedGraph<V, E> graph, V start) {
		this.graph = graph;
		vertexes.add(start);
		current = start;
		depth = 0;
	}

	/**
	 * 
	 * Creates a new instance of DirectedPath.
	 *
	 * @param path
	 * @param edge
	 */
	public DirectedPath(DirectedPath<V, E> path, E edge) {
		this.graph = path.getGraph();
		this.vertexes.addAll(path.getVertexes());
		this.edges.addAll(path.getEdges());
		this.current = (V) graph.findVertexByUniqueKey(edge.to());
		this.vertexes.add(this.current);
		this.edges.add(edge);
		this.depth = path.getDepth() + 1;

	}

	/**
	 * @return the vertexes
	 */
	public List<V> getVertexes() {
		return vertexes;
	}

	/**
	 * @return the edges
	 */
	public List<E> getEdges() {
		return edges;
	}

	/**
	 * @return the current
	 */
	public V getCurrent() {
		return current;
	}

	/**
	 * @return the graph
	 */
	public DirectedGraph<V, E> getGraph() {
		return graph;
	}

	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * 
	 * @Description: visit: 
	 * 		At the current vertex of the path , Try to visit a edge.
	 * @param edge
	 * @throws GraphException void
	 */
	public void visit(E edge) throws GraphException {
		if (!edge.from().equals(current.getUniqueKey())) {
			throw new GraphException("Invalid visit.");
		}
		edges.add(edge);
		this.current = (V) graph.findVertexByUniqueKey(edge.to());
		vertexes.add(this.current);

	}

	/**
	 * 
	 * @Description: visit: 
	 * 		visit the next degree .
	 * @return List<DirectedPath<V,E>>
	 */
	public List<DirectedPath<V, E>> visit() {
		List<DirectedPath<V, E>> result = current.getEdges().stream().map(edge -> {
			return new DirectedPath<V, E>(this, edge);
		}).collect(toList());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getVertexes().stream().map(V::toString).collect(joining("-"));
	}

}
