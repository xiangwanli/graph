/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: The Edge with direction Data Model
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.model;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: DirectedEdge
 * @Description: The Edge with direction Data Model
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class DirectedEdge extends Edge {

	/**
	 * 
	 * Creates a new instance of DirectedEdge.
	 *
	 * @param from
	 * @param to
	 */
	public DirectedEdge(String from, String to) {
		super(from, to);
		this.uniqueKey = from + "-" + to;
	}

	/**
	 * 
	 * @Description: from: get the start vertex.
	 * @return String
	 */
	public String from() {
		return vKey1;
	}

	/**
	 * 
	 * @Description: to: get the end vertex.
	 * @return String
	 */
	public String to() {
		return vKey2;
	}

	/*
	 * (non-Javadoc)
	 * @see com.trains.graph.model.Edge#access(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean access(String vKey1, String vKey2) {
		return (vKey1.equals(this.vKey1) && vKey2.equals(this.vKey2));
	}

	/*
	 * (non-Javadoc)
	 * @see com.trains.graph.model.Edge#getFromVertexes()
	 */
	@Override
	public List<String> getFromVertexes() {
		return Arrays.asList(from());
	}

	/*
	 * (non-Javadoc)
	 * @see com.trains.graph.model.Edge#toString()
	 */
	@Override
	public String toString() {
		return new StringBuffer().append("{from[").append(vKey1).append("],to[").append(vKey2).append("]}").toString();
	}
}
