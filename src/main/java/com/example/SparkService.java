package com.example;

import spark.utils.StringUtils;

import static spark.Spark.clusterSession;
import static spark.Spark.get;


/**
 * Created by bhuva on 24-10-2017.
 */
public class SparkService {

    public static void main(String[] args) {

        // Use below code to enable session clustering with MongoDB
        // session clustering with mongodb
        clusterSession("node-01"
                ,null
                ,"HttpSessionDatastore"
                ,"JettySessions"
                ,"mongodb://httpsessiondbuser:password@localhost:27017/?authSource=admin"
                ,600
        );


       /*
         Use below code to enable session clustering with JDBC
        //session clustering with JDBC
            clusterSession("node-01"
                    ,"com.mysql.jdbc.Driver"
                    ,"HttpSessionDatastore"
                    ,"JettySessions"
                    ,"jdbc:mysql://localhost:3306/HttpSessionDatastore?user=root&password=root"
                    ,600
            );
       */

         get("/store", (request, response) -> {

            request.session().attribute("data","This is session data !!!");
            return "Data has been stored to session.";
        });

        get("/display", (request, response) -> {
            String sessionData = "No data found in session !!!";
            return StringUtils.isBlank(request.session().attribute("data"))? sessionData : request.session().attribute("data").toString();
        });


    }

}
