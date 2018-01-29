# craftdemo-online
== Craftdemo online Application

i
This example application demonstrates how easy it is to get started with http://neo4j.com/developer[Neo4j] in Java.

It is a very simple web application that uses our SBG graph dataset to provide a search with listing, a detail view and a graph visualization.

THe backend is implemented in Java using spark-java a lightweight web framework.

Using Neo4j from Java is as easy as using any other database that offers a textual query language.
With our binary protocol driver for the binary protocol, you get a clean API to manage your `Session`, `Transaction` and `Statement` and iterate over each `Value` of a `StatementResult` with a rich conversion DSL.

=== The Stack

These are the components of our min- Web Application:

* Application Type:         Java-Web Application
* Web framework:            http://www.sparkjava.com/[Spark-Java] (Micro-Webframework)
* Neo4j Database Connector: https://github.com/neo4j/neo4j-java-driver[Neo4j-Java-Driver] for Cypher http://neo4j.com/docs/developer-manual/current/#driver-manual-index[Docs]
* Database:                 Neo4j-Server


=== Endpoints:

Get User relation ship 

----

// list of JSON objects for user search results

curl http://localhost:8080/search?q=Howard%20Roark


=== Setup

Spark is a micro-webframework to easily define routes for endpoints and provide their implementation.
In our case the implementation calls the `UserService` which has one method per endpoint that returns Java collections
which are turned into JSON using the Google Gson library.

The `UserService` uses the Neo4j-Java driver to execute queries via Neo4j binary protocol "Bolt".
You add the dependency to the Neo4j-Java-Driver driver in your `pom.xml`:

[source,xml]
----
<dependency>
    <groupId>org.neo4j.driver</groupId>
    <artifactId>neo4j-java-driver</artifactId>
    <version>1.0.0-RC2</version>
</dependency>
----

To use the driver, you provide a connection URL, e.g. `bolt://user:password@localhost`, get a `Driver` instance, from which you get a Session.
The session allows you to execute Cypher statements directly and iterate over the `StatementResults` to access the resulting `Value`s.
Please edit com.craftdemo.util.Util.java to add your connection settings, for instance "bolt://neo4j:neo4j@localhost"

W
----

=== Run locally:

Start your local Neo4j Server (http://neo4j.com/download[Download & Install]), open the http://localhost:7474[Neo4j Browser].
After logging in, install the User graph data set by entering the `:play user` command, click the CREATE-statement, and hit the triangular "Run" button.

Start this application with:

[source,shell]
----
mvn compile exec:java
----
