# Integrating Hadoop with MongoDB  

This repository provides a step-by-step guide to integrating **Hadoop** with **MongoDB** for seamless data processing.  

## A) Hadoop Installation  
Follow the YouTube instructions in this video: [https://youtu.be/knAS0w-jiUk?si=1kRWjsqXUrt669Vl](link) 

## B) Install MongoDB and MongoDB Shell  
Follow the instructions in this video: [https://youtu.be/tC49Nzm6SyM?si=wiXDoVOhadirJ1yj](link)

## C) Creating an Account on MongoDB Atlas  
Create a free account [here](https://www.mongodb.com/lp/cloud/atlas/try4-reg?utm_source=google&utm_campaign=search_gs_pl_evergreen_atlas_core-high-int_prosp-brand_gic-null_apac-in_ps-all_desktop_eng_lead&utm_term=mongodb%20atlas&utm_medium=cpc_paid_search&utm_ad=e&utm_ad_campaign_id=19617021259&adgroup=173739098353&cq_cmp=19617021259&gad_source=1&gclid=Cj0KCQiAz6q-BhCfARIsAOezPxmZymiHYComojgIWYsBuqx561BsxSQsS0eK6rHolondkg5Mzcd3N6YaAruOEALw_wcB).

> **Note:** Use your **Google Account** to create the MongoDB account.  

## D) Download Necessary JAR Files  
Download the required JAR files from [https://repo1.maven.org/maven2/org/mongodb/](link) (Download only the `.jar` files):  

- `commons-lang-2.6`  
- `mongodb-driver-3.12.10`  
- `mongodb-driver-core-3.12.10`  
- `mongo-hadoop-core-2.0.2`  
- `bson-4.9.1`  
- `mongodb-driver-sync-4.9.1`  

> **Note:** Store these files in the following directory:  
> ```
> C:/hadoop/share/hadoop/common/lib/
> ```

## E) Add the below properties in `C:\hadoop\etc\hadoop\core-site.xml`
Make sure to replace mongo.input.uri value and mongo.output.uri value by your own MonogoDB data
```xml
<property>
    <name>fs.defaultFS</name>
    <value>hdfs://localhost:9000</value>
</property>

<property>
    <name>mongo.input.uri</name>
    <value>mongodb://localhost:27017/mydatabase.input_collection</value>
</property>

<property>
    <name>mongo.output.uri</name>
    <value>mongodb://localhost:27017/mydatabase.output_collection</value>
</property>

<property>
    <name>mongo.input.uri</name>
    <value>mongodb://<username>:<password>@<cluster-address>/<database>.<collection></value>
</property>

<property>
    <name>mongo.output.uri</name>
    <value>mongodb://<username>:<password>@<cluster-address>/<database>.<collection></value>
</property>

```

## F) Integarting Hadoop with MongoDB
> **Note** Make sure your Hadoop is Running
1. Open IntelliJ and create a new project with Maven selected.
2. Add `dependecies` and `build plugins` in pom.xml file from the given pom.xml file
3. Create a file name `MongoDBInsert.java` in org.example and run the file. (It will upload the data to MongoDB database)
4. Open cmd and run
> ```
> mongosh “your uri of MongoDB”
> use testDB
> db.usersNew.find().pretty()
> ```

5. Create 3 more file named `MongoMapper.java`, `MongoReducer` and `MongoHadoopJob.java` and run the MongoHadoopJob.java file it will read the data from MongoDb and process it after that store it back to MongoDB.
6.  Run the below command on cmd
> ```
> db.processedUsersData.find().pretty()
> ```
