# Session Persistence Using SparkJava

This service is to test session persistence using SparkJava with MongoDB and JDBC

## Getting Started

* SparkJava version spark-core-2.6.0 not have feature to support Session Clustering.
* To enable session clustering in your project, you need to use Customised Spark Java lib spark-core-2.6.1-SNAPSHOT.jar (SessionPersistenceSpark/libs/spark-core-2.6.1-SNAPSHOT.jar).
* In Customised spark-core-2.6.1-SNAPSHOT.jar i have added feature to handle Mongo and JDBC based session persistence.

### Prerequisites

* To implement Mongo Based Session persistence. you need to install Mongo database and need to create required user with correct privileges.
* To implement JDBC Based Session persistence. you need to install MySql database and need to create required database and user with correct privileges.

## Mongo Based Session Clustering

To enable Mongo based session clustering

```
 clusterSession("node-01"
                ,null
                ,"HttpSessionDatastore"
                ,"JettySessions"
                ,"mongodb://httpsessiondbuser:password@localhost:27017/?authSource=admin"
                ,600
        );

 - clusterNodeName                  : should be unique clusterNodeName per jetty instance
 - clusterDatastoreDriverClassName  : driver used to connect to the datasource (ie jdbc driver not required to Mongodb)
 - clusterDatastoreName             :datastore name used to create database in mongodb
 - clusterCollectionName                 : collection name used to create collection in mongodb
 - clusterDatastoreDriverConnectionUrl   : url used to connect to the datasource (ie jdbc or mongodb url)
 - clusterScavengeInterval               : scavenge time sync up (in seconds)

```

## JDBC Based Session Clustering

To enable JDBC based session clustering

```
 clusterSession("node-01"
                 ,"com.mysql.jdbc.Driver"
                 ,"HttpSessionDatastore"
                 ,"JettySessions"
                 ,"jdbc:mysql://localhost:3306/HttpSessionDatastore?user=root&password=root"
                 ,600
         );

 - clusterNodeName                  : should be unique clusterNodeName per jetty instance
 - clusterDatastoreDriverClassName  : driver used to connect to the datasource (ie jdbc driver not required to Mongodb)
 - clusterDatastoreName             :datastore name used to create database in mongodb
 - clusterCollectionName                 : collection name used to create collection in mongodb
 - clusterDatastoreDriverConnectionUrl   : url used to connect to the datasource (ie jdbc or mongodb url)
 - clusterScavengeInterval               : scavenge time sync up (in seconds)

```


## Running the tests

Run below gradle command to start service

```
gradle clean build run

```

Once service started, service will be accessible on http://127.0.0.1:4567.
After running below 3 steps you can see your session stored into Mongo/Mysql database.

1) access service using
```
http://127.0.0.1:4567/display

```
you will see message on browser 'No data found in session !!!'

2) run below to store data into session
```
http://127.0.0.1:4567/store

```
you will see message on browser 'Data has been stored to session'

3) run below to display session data
```
http://127.0.0.1:4567/display

```
you will see message on browser 'This is session data !!!'


## Authors

* **Dipakkumar Bhoova** -  [bhuva3](https://github.com/bhuva3)

