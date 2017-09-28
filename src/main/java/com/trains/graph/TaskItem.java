/**
 * The Train Problems
 * @Title: com.trains.graph
 * @Description: The Problem Task
 * @author: xiangwanli
 * @date 2017年4月9日
 * @version 1.0.0
 */
package com.trains.graph;

/**
 * @ClassName: TaskItem
 * @Description: The Problem Task
 * @author xiangwanli
 * @date 2017年4月9日
 *
 */
public abstract class TaskItem implements Runnable {

	private int taskId;
	
	/**
	 * 
	 * Creates a new instance of TaskItem.
	 *
	 * @param taskId
	 */
	public TaskItem(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * @return the taskId
	 */
	public int getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId
	 *            the taskId to set
	 */
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	/**
	 * 
	 * @Description: printResult: 
	 * 		print the output according to the expected format.
	 * @param result void
	 */
	public void printResult(String result) {
		System.out.println(new StringBuffer("Output #").append(this.taskId).append(":").append(result).toString());
	}

}
