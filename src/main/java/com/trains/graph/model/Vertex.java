/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: TODO
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: Vertex
 * @Description: The Vertex Data Model
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class Vertex<E extends Edge> {

	private String uniqueKey;

	private List<E> edges;

	/**
	 * 
	 * Creates a new instance of Vertex.
	 *
	 * @param uniqueKey
	 */
	public Vertex(String uniqueKey) {
		this.uniqueKey = uniqueKey;
		edges = new ArrayList<E>();
	}

	/**
	 * 
	 * Creates a new instance of Vertex.
	 *
	 * @param uniqueKey
	 * @param edges
	 */
	public Vertex(String uniqueKey, List<E> edges) {
		this.uniqueKey = uniqueKey;
		this.edges = edges != null ? edges : new ArrayList<E>();
	}

	/**
	 * @return the uniqueKey
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}

	/**
	 * @param uniqueKey the uniqueKey to set
	 */
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}

	/**
	 * @return the edges
	 */
	public List<E> getEdges() {
		return edges;
	}

	/**
	 * @param edges the edges to set
	 */
	public void setEdges(List<E> edges) {
		this.edges = edges;
	}

	/**
	 * 
	 * @Description: get: get it's own.
	 * @return Vertex<E>
	 * 
	 * @throws
	 */
	public Vertex<E> get() {
		return this;
	}

	/**
	 * 
	 * @Description: addEdge: Add the edge to the edge list of the vertex.
	 * @param edge void
	 * 
	 * @throws
	 */
	public void addEdge(E edge) {
		this.edges.add(edge);
	}

	/**
	 * 
	 * @Description: visit: Check if the vertex could visit another vertex directly.
	 * @param vertex
	 * @return E
	 * 
	 * @throws
	 */
	public E visit(Vertex<E> vertex) {
		for (E edge : edges) {
			if (edge.access(this.getUniqueKey(), vertex.getUniqueKey())) {
				return edge;
			}
		}
		return null;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vertex) {
			Vertex<?> vertex = (Vertex<?>) obj;
			return this.getUniqueKey().equals(vertex.getUniqueKey());
		}
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.getUniqueKey().hashCode();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.getUniqueKey();
	}

	
}
