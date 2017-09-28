The Train Problems
=====

The purpose of this problem is to help the railroad provide its customers with information about the routes.  
In particular, it will compute the distance along a certain route, the number of different routes between two towns, 
and the shortest route between two towns, etc.

## To build Trains-Graph
- install JDK 1.8 or higher
- install maven 3.5.0 or higher - http://maven.apache.org/download.html

Enter the following:

	$ unzip Trains-Java-XiangWanLi.zip
	$ cd graph
	$ mvn clean install
	$ cd target
	$ java -jar trains-graph-0.0.1.jar
	
you can find the deployment artifacts in the "graph/target" directory once the build is completed.

## Design
- package com.trains.graph.model is for the basic data model of the graph.
- package com.trains.graph.business is for the business model of the trains problems.
- package com.trains.graph.algorithm is for the graph traversal algorithms that are used to fix trains problems.
- package com.trains.graph is used to show the request case result.
- The trains problem is a graph traversal problem , the towns are vertex , the routes are edges.
- Mainly using breath first search way to do the multiple degrees query.
- The Paths(RoutePath data model) are the traversal result , and we can calculate the all the expected result that based on the paths.