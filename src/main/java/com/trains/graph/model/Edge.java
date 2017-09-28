/**
 * The Train Problems
 * @Title: com.trains.graph.model
 * @Description: The Edge Data Model
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph.model;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName: Edge
 * @Description: The Edge Data Model
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public class Edge {

	protected String vKey1;

	protected String vKey2;

	protected String uniqueKey;

	/**
	 * 
	 * Creates a new instance of Edge.
	 *
	 * @param vKey1
	 * @param vKey2
	 */
	public Edge(String vKey1, String vKey2) {
		super();
		this.vKey1 = vKey1;
		this.vKey2 = vKey2;
		if (this.vKey1.compareTo(this.vKey2) >= 0) {
			this.uniqueKey = vKey1 + "-" + vKey2;
		} else {
			this.uniqueKey = vKey2 + "-" + vKey1;
		}

	}

	/**
	 * @return the vKey1
	 */
	public String getvKey1() {
		return vKey1;
	}

	/**
	 * @return the vKey2
	 */
	public String getvKey2() {
		return vKey2;
	}

	/**
	 * @return the uniqueKey
	 */
	public String getUniqueKey() {
		return uniqueKey;
	}

	/**
	 * 
	 * @Description: get: get it's own.
	 * @return Edge
	 */
	public Edge get() {
		return this;
	}

	/**
	 * 
	 * @Description: getVertexes: get key list of the vertex at the two sides of the edge.
	 * @return List<String>
	 */
	public List<String> getVertexes() {
		return Arrays.asList(vKey1, vKey2);
	}

	/**
	 * 
	 * @Description: getFromVertexes: get key list of the from vertex of the edge.
	 * @return List<String>
	 */
	public List<String> getFromVertexes() {
		return Arrays.asList(vKey1, vKey2);
	}
	
	/**
	 * 
	 * @Description: access: Check if there is a route from one to another
	 * @param vKey1
	 * @param vKey2
	 * @return boolean
	 */
	public boolean access(String vKey1, String vKey2) {

		return (vKey1.equals(this.vKey1) && vKey2.equals(this.vKey2))
				|| (vKey1.equals(this.vKey2) && vKey2.equals(this.vKey1));

	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Edge) {
			Edge edge = (Edge) obj;
			return this.getUniqueKey().equals(edge.getUniqueKey());
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
