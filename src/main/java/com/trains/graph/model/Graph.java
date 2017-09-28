/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: The Graph Data Model
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.trains.graph.ex.DuplicateException;
import com.trains.graph.ex.EdgeNotFoundExeption;
import com.trains.graph.ex.GraphException;
import com.trains.graph.ex.VertexNotFoundExeption;

import static java.util.stream.Collectors.toList;

/**
 * @ClassName: Grpah
 * @Description: The Graph Data Model
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class Graph<V extends Vertex<E>, E extends Edge> {

	/**
	 * The map between the vertex and its key.
	 */
	private Map<String, V> vertexKeyMap = new HashMap<String, V>();

	/**
	 * The map between the edge and its key.
	 */
	private Map<String, E> edgeKeyMap = new HashMap<String, E>();

	/**
	 * 
	 * Creates a new instance of Graph.
	 *
	 * @param vertexes
	 * @param edges
	 * @throws GraphException
	 */
	public Graph(List<V> vertexes, List<E> edges) throws GraphException {
		for (V vertex : vertexes) {
			addVertex(vertex);
		}
		for (E edge : edges) {
			addEdge(edge);
		}
	}

	/**
	 * @return the vertexKeyMap
	 */
	public Map<String, V> getVertexKeyMap() {
		return vertexKeyMap;
	}

	/**
	 * @param vertexKeyMap
	 *            the vertexKeyMap to set
	 */
	public void setVertexKeyMap(Map<String, V> vertexKeyMap) {
		this.vertexKeyMap = vertexKeyMap;
	}

	/**
	 * @return the edgeKeyMap
	 */
	public Map<String, E> getEdgeKeyMap() {
		return edgeKeyMap;
	}

	/**
	 * @param edgeKeyMap
	 *            the edgeKeyMap to set
	 */
	public void setEdgeKeyMap(Map<String, E> edgeKeyMap) {
		this.edgeKeyMap = edgeKeyMap;
	}

	/**
	 * 
	 * @Description: addVertex: Add a vertex to the graph
	 * @param v
	 * @throws GraphException 
	 * void
	 */
	public void addVertex(V v) throws GraphException {
		if (vertexKeyMap.containsKey(v.getUniqueKey())) {
			throw new DuplicateException();
		} else {
			vertexKeyMap.put(v.getUniqueKey(), v);
		}
	}

	/**
	 * 
	 * @Description: addEdge: Add an edge to the graph.
	 * @param edge
	 * @throws GraphException 
	 * void
	 */
	public void addEdge(E edge) throws GraphException {
		if (edgeKeyMap.containsKey(edge.getUniqueKey())) {
			throw new DuplicateException();
		} else {
			List<String> vertexes = edge.getVertexes();
			for (String v : vertexes) {
				if (!vertexKeyMap.containsKey(v)) {
					throw new VertexNotFoundExeption();
				}
			}
			edgeKeyMap.put(edge.getUniqueKey(), edge);
			List<String> fromVertexes = edge.getFromVertexes();
			V v = null;
			for (String vKey : fromVertexes) {
				v = findVertexByUniqueKey(vKey);
				v.addEdge(edge);
			}
		}
	}

	/**
	 * 
	 * @Description: deleteVertex: Delete a vertex from the graph.
	 * @param uniqueKey
	 * @throws GraphException 
	 * void
	 */
	public void deleteVertex(String uniqueKey) throws GraphException {
		V v = vertexKeyMap.get(uniqueKey);
		if (v != null) {
			v.getEdges().forEach(e -> this.edgeKeyMap.remove(e.getUniqueKey()));
			vertexKeyMap.remove(uniqueKey);
		} else {
			throw new VertexNotFoundExeption();
		}
	}
	
	/**
	 * 
	 * @Description: deleteEdge: Delete an edge from the graph.
	 * @param uniqueKey
	 * @throws GraphException 
	 * void
	 */
	public void deleteEdge(String uniqueKey) throws GraphException {
		if (this.edgeKeyMap.containsKey(uniqueKey)) {
			E e = this.edgeKeyMap.get(uniqueKey);
			for (String vKey : e.getFromVertexes()) {
				V v = this.vertexKeyMap.get(vKey);
				if (v != null) {
					List<E> edges = v.getEdges().stream().filter(ve -> !ve.getUniqueKey().equals(e.getUniqueKey()))
							.collect(toList());
					v.setEdges(edges);
				}
			}
			this.edgeKeyMap.remove(uniqueKey);
		} else {
			throw new EdgeNotFoundExeption();
		}
	}
	
	/**
	 * 
	 * @Description: updateVertex: update a vertex in the graph.
	 * @param v
	 * @throws GraphException 
	 * void
	 */
	public void updateVertex(V v) throws GraphException {
		if (v != null && this.vertexKeyMap.containsKey(v.getUniqueKey())) {
			this.vertexKeyMap.put(v.getUniqueKey(), v);
		} else {
			throw new VertexNotFoundExeption();
		}
	}
	
	/**
	 * 
	 * @Description: updateEdge: Update an edge from the graph.s
	 * @param e
	 * @throws GraphException 
	 * void
	 */
	public void updateEdge(E e) throws GraphException {
		if (e != null && this.edgeKeyMap.containsKey(e.getUniqueKey())) {
			for (String vKey : e.getFromVertexes()) {
				V v = this.vertexKeyMap.get(vKey);
				if (v != null) {
					List<E> edges = v.getEdges().stream().filter(ve -> ve.getUniqueKey().equals(e.getUniqueKey()))
							.collect(toList());
					edges.add(e);
					v.setEdges(edges);
				}
			}
		} else {
			throw new EdgeNotFoundExeption();
		}
	}
	
	/**
	 * 
	 * @Description: findVertexByUniqueKey: find the vertex by key.
	 * @param uniqueKey
	 * @return V
	 */
	public V findVertexByUniqueKey(String uniqueKey) {
		return this.vertexKeyMap.get(uniqueKey);
	}

	/**
	 * 
	 * @Description: findEdgeByUniqueKey: find the edge by key.
	 * @param uniqueKey
	 * @return E
	 */
	public E findEdgeByUniqueKey(String uniqueKey) {
		return this.edgeKeyMap.get(uniqueKey);
	}

}
