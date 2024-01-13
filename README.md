# OracleExporter

OracleExporter is a Free Java-based tool that allows you to export any object saved in an Oracle database.
It can export the Data Definition Language (DDL) for 74 distinct object types, including tables, package bodies and
specifications, views, procedures, triggers, and more.
OracleExporter is easily configurable through a JSON file and supports multi-schema and multi-datasource. 
It allows you to export a single object or entire object class. It performs object backups to file and is easy to 
integrate into CI/CD pipelines.

## Features

* Run on command line
* Supports 74 distinct object types
* Easily configurable through a JSON file
* Supports multi-schema and multi-datasource
* Allows you to export a single object or entire class of object types
* Performs object backups to file
* Easy to integrate into CI/CD pipelines

## Oracle types

This are supported Oracle Types: 

* AQ_QUEUE
* AQ_QUEUE_TABLE
* AQ_TRANSFORM
* ASSOCIATION
* AUDIT
* AUDIT_OBJ
* CLUSTER
* COMMENT
* CONSTRAINT
* CONTEXT
* DATABASE_EXPORT
* DB_LINK
* DEFAULT_ROLE
* DIMENSION
* DIRECTORY
* FGA_POLICY
* FUNCTION
* INDEX_STATISTICS
* INDEX
* INDEXTYPE
* JAVA_SOURCE
* JOB
* LIBRARY
* MATERIALIZED_VIEW
* MATERIALIZED_VIEW_LOG
* OBJECT_GRANT
* OPERATOR
* PACKAGE
* PACKAGE_SPEC
* PACKAGE_BODY
* PROCEDURE
* PROFILE
* PROXY
* REF_CONSTRAINT
* REFRESH_GROUP
* RESOURCE_COST
* RLS_CONTEXT
* RLS_GROUP
* TABLE_DATA
* TABLE_EXPORT
* TABLE_STATISTICS
* TABLESPACE
* TABLESPACE_QUOTA
* TRANSPORTABLE_EXPORT
* TRIGGER
* TRUSTED_DB_LINK
* TYPE
* TYPE_SPEC
* TYPE_BODY
* USER
* VIEW
* SEQUENCE
* SYNONYM
* SYSTEM_GRANT
* TABLE
* XMLSCHEMA
* XS_USER
* XS_ROLE
* XS_ROLESET
* XS_ROLE_GRANT
* XS_SECURITY_CLASS
* XS_DATA_SECURITY
* XS_ACL
* XS_ACL_PARAM
* XS_NAMESPACE
* RLS_POLICY
* RMGR_INTITIAL_CONSUMER_GROUP
* RMGR_CONSUMER_GROUP
* RMGR_PLAN
* RMGR_PLAN_DIRECTIVE
* ROLE
* ROLE_GRANT
* ROLLBACK_SEGMENT
* SCHEMA_EXPORT

## Usage

To use OracleExporter, you can create a JSON configuration file (datasource.json) or use command line directly. 
The configuration file defines the connection information for your Oracle database, as well as the objects you want to export.
Once you have created the configuration file, you can run OracleExporter. 
The tool will connect to your database and export the specified objects to file.

## Datasource File (datasource.json)

The datasource.json file must be located in a "conf" directory where oracleexporter.jar is located.

### Single Object export

```
[{
"url": "jdbc:oracle:thin:@localhost:1521:xe",
"user": "my_user",
"password": "my_password",
"enabled": "true",
"exports" : [{
    "type": "PACKAGE_BODY",
    "name": "MY_PACKAGE",
    "file": "/tmp/MY_PACKAGE.pkb"
    }]
}]
```
The provided JSON object represents a configuration for exporting a specific Oracle object, 
namely the package body named "MY_PACKAGE", to a file named "/tmp/MY_PACKAGE.pkb".

Here's a breakdown of the object's properties:

* url: Specifies the JDBC URL for connecting to the Oracle database. 
In this case, it's "jdbc:oracle:thin:@localhost:1521:xe", 
which indicates a local connection to an Oracle database instance running on port 1521 with SID "xe".

* user: Specifies the database username to use for connecting to the database. In this case, it's "my_user".
* password: Specifies the password for the connecting database user. In this case, it's "my_password".

* enabled: A boolean flag indicating whether the configuration is enabled. 
* In this case, it's "true", meaning the configuration is active and should be applied.

* exports: An array of objects representing the specific objects to be exported. In this case, there's only one object defined:
  - type: Specifies the type of object to be exported. In this case, it's "PACKAGE_BODY", which indicates the package body to export.
  - name: Specifies the name of the object to be exported. In this case, it's "MY_PACKAGE".
  - file: Specifies the path to the file where the exported object should be saved. In this case, it's "/tmp/MY_PACKAGE.pkb", meaning the package body will be saved to this file.

### Multiple Object Export

```
[{
"url": "jdbc:oracle:thin:@localhost:1521:xe",
"user": "my_user",
"password": "my_password",
"enabled": "true",
"exports" : [{
"type": "PACKAGE_BODY",
"name": "*",
"file": "/tmp"
}]
}]
```

The provided JSON object represents a configuration for exporting all PACKAGE_BODY to 
directory named "/tmp".

Here's a breakdown of the object's properties:

* url: Specifies the JDBC URL for connecting to the Oracle database.
  In this case, it's "jdbc:oracle:thin:@localhost:1521:xe",
  which indicates a local connection to an Oracle database instance running on port 1521 with SID "xe".

* user: Specifies the database username to use for connecting to the database. In this case, it's "my_user".
* password: Specifies the password for the connecting database user. In this case, it's "my_password".

* enabled: A boolean flag indicating whether the configuration is enabled.
* In this case, it's "true", meaning the configuration is active and should be applied.

* exports: An array of objects representing the specific objects to be exported. In this case, there's only one object defined:
    - type: Specifies the type of object to be exported. In this case, it's "PACKAGE_BODY", which indicates the package body to export.
    - name: Specifies "*" for all database PACKAGE_BODY.
    - file: Specifies the path to the file where the exported object should be saved. In this case, it's "/tmp", meaning all the packages body will be saved to this directory.


## Installation

To install OracleExporter, you can use the following command:

mvn clean dependency:copy-dependencies install -DskipTests

This will build the OracleExporter JAR file. 

## Program Parameters

You can then run the tool using the following command:
```
* usage: java -jar OracleExporter.jar
* -d,--datasource <arg>          Specify datasource url.
* -f,--outputdestination <arg>   Specify a file name or directory.
* -i,--importtype <arg>          Specify commandline or config.
* -n,--oracletypename <arg>      Specify a single name or * for get all.
* -o,--oracletype <arg>          Specify a valid oracletype (es.PACKAGE_BODY).
* -p,--password <arg>            Specify schema password.
* -u,--user <arg>                Specify database schema user.
```

## Contributing

OracleExporter is an open source project. You can contribute to the project by submitting pull requests or reporting issues.

## License

OracleExporter is licensed under the Apache License, Version 2.0.

## Contact

If you have any questions or feedback, please contact the project maintainers at oracleexporter@alessandroferrari.net.
(https://www.alessandroferrari.net)