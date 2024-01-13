/*
 * OracleExporter
 * Copyright (c) 2023-2024 Alessandro Ferrari
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package net.alessandroferrari.oracleexporter.model;
import java.util.*;

/**
 * The type Oracle type.
 *
 * Table 109-12 DBMS_METADATA: Object Types
 * https://docs.oracle.com/en/database/oracle/oracle-database/19/arpls/DBMS_METADATA.html#GUID-B1551D25-8F57-4D00-BB1D-7A611A66764D
 */

public class OracleType {
    private OracleType () {}

    private static final String AQ_QUEUE = "AQ_QUEUE";
    private static final String AQ_QUEUE_TABLE = "AQ_QUEUE_TABLE";
    private static final String AQ_TRANSFORM = "AQ_TRANSFORM";
    private static final String ASSOCIATION = "ASSOCIATION";
    private static final String AUDIT = "AUDIT";
    private static final String AUDIT_OBJ = "AUDIT_OBJ";
    private static final String CLUSTER = "CLUSTER";
    private static final String COMMENT = "COMMENT";
    private static final String CONSTRAINT = "CONSTRAINT";
    private static final String CONTEXT = "CONTEXT";
    private static final String DATABASE_EXPORT = "DATABASE_EXPORT";
    private static final String DB_LINK = "DB_LINK";
    private static final String DEFAULT_ROLE = "DEFAULT_ROLE";
    private static final String DIMENSION = "DIMENSION";
    private static final String DIRECTORY = "DIRECTORY";
    private static final String FGA_POLICY = "FGA_POLICY";
    private static final String FUNCTION = "FUNCTION";
    private static final String INDEX_STATISTICS = "INDEX_STATISTICS";
    private static final String INDEX = "INDEX";
    private static final String INDEXTYPE = "INDEXTYPE";
    private static final String JAVA_SOURCE = "JAVA_SOURCE";
    private static final String JOB = "JOB";
    private static final String LIBRARY = "LIBRARY";
    private static final String MATERIALIZED_VIEW = "MATERIALIZED_VIEW";
    private static final String MATERIALIZED_VIEW_LOG = "MATERIALIZED_VIEW_LOG";
    private static final String OBJECT_GRANT = "OBJECT_GRANT";
    private static final String OPERATOR = "OPERATOR";
    private static final String PACKAGE = "PACKAGE";
    private static final String PACKAGE_SPEC = "PACKAGE_SPEC";
    private static final String PACKAGE_BODY = "PACKAGE_BODY";
    private static final String PROCEDURE = "PROCEDURE";
    private static final String PROFILE = "PROFILE";
    private static final String PROXY = "PROXY";
    private static final String REF_CONSTRAINT = "REF_CONSTRAINT";
    private static final String REFRESH_GROUP = "REFRESH_GROUP";
    private static final String RESOURCE_COST = "RESOURCE_COST";
    private static final String RLS_CONTEXT = "RLS_CONTEXT";
    private static final String RLS_GROUP = "RLS_GROUP";
    private static final String TABLE_DATA = "TABLE_DATA";
    private static final String TABLE_EXPORT = "TABLE_EXPORT";
    private static final String TABLE_STATISTICS = "TABLE_STATISTICS";
    private static final String TABLESPACE = "TABLESPACE";
    private static final String TABLESPACE_QUOTA = "TABLESPACE_QUOTA";
    private static final String TRANSPORTABLE_EXPORT = "TRANSPORTABLE_EXPORT";
    private static final String TRIGGER = "TRIGGER";
    private static final String TRUSTED_DB_LINK = "TRUSTED_DB_LINK";
    private static final String TYPE = "TYPE";
    private static final String TYPE_SPEC = "TYPE_SPEC";
    private static final String TYPE_BODY = "TYPE_BODY";
    private static final String USER = "USER";
    private static final String VIEW = "VIEW";
    private static final String SEQUENCE = "SEQUENCE";
    private static final String SYNONYM = "SYNONYM";
    private static final String SYSTEM_GRANT = "SYSTEM_GRANT";
    private static final String TABLE = "TABLE";
    private static final String XMLSCHEMA = "XMLSCHEMA";
    private static final String XS_USER = "XS_USER";
    private static final String XS_ROLE = "XS_ROLE";
    private static final String XS_ROLESET = "XS_ROLESET";
    private static final String XS_ROLE_GRANT = "XS_ROLE_GRANT";
    private static final String XS_SECURITY_CLASS = "XS_SECURITY_CLASS";
    private static final String XS_DATA_SECURITY = "XS_DATA_SECURITY";
    private static final String XS_ACL = "XS_ACL";
    private static final String XS_ACL_PARAM = "XS_ACL_PARAM";
    private static final String XS_NAMESPACE = "XS_NAMESPACE";
    private static final String RLS_POLICY = "RLS_POLICY";
    private static final String RMGR_INTITIAL_CONSUMER_GROUP = "RMGR_INTITIAL_CONSUMER_GROUP";
    private static final String RMGR_CONSUMER_GROUP = "RMGR_CONSUMER_GROUP";
    private static final String RMGR_PLAN = "RMGR_PLAN";
    private static final String RMGR_PLAN_DIRECTIVE = "RMGR_PLAN_DIRECTIVE";
    private static final String ROLE = "ROLE";
    private static final String ROLE_GRANT = "ROLE_GRANT";
    private static final String ROLLBACK_SEGMENT = "ROLLBACK_SEGMENT";
    private static final String SCHEMA_EXPORT = "SCHEMA_EXPORT";

    private static final String WILD_XML_SCHEMA = "XML SCHEMA";
    private static final String WILD_DATABASE_LINK = "DATABASE LINK";
    private static final String WILD_JAVA_SOURCE = "JAVA SOURCE";
    private static final String WILD_MATERIALIZED_VIEW = "MATERIALIZED VIEW";
    private static final String WILD_PACKAGE_BODY = "PACKAGE BODY";
    private static final String WILD_TYPE_BODY = "TYPE BODY";

    private static final List<String> oracleTypes = new ArrayList<>(
            Arrays.asList(
                    AQ_QUEUE,
                    AQ_QUEUE_TABLE,
                    AQ_TRANSFORM,
                    ASSOCIATION,
                    AUDIT,
                    AUDIT_OBJ,
                    CLUSTER,
                    COMMENT,
                    CONSTRAINT,
                    CONTEXT,
                    DATABASE_EXPORT,
                    DB_LINK,
                    DEFAULT_ROLE,
                    DIMENSION,
                    DIRECTORY,
                    FGA_POLICY,
                    FUNCTION,
                    INDEX_STATISTICS,
                    INDEX,
                    INDEXTYPE,
                    JAVA_SOURCE,
                    JOB,
                    LIBRARY,
                    MATERIALIZED_VIEW,
                    MATERIALIZED_VIEW_LOG,
                    OBJECT_GRANT,
                    OPERATOR,
                    PACKAGE,
                    PACKAGE_SPEC,
                    PACKAGE_BODY,
                    PROCEDURE,
                    PROFILE,
                    PROXY,
                    REF_CONSTRAINT,
                    REFRESH_GROUP,
                    RESOURCE_COST,
                    RLS_CONTEXT,
                    RLS_GROUP,
                    RLS_POLICY,
                    RMGR_CONSUMER_GROUP,
                    RMGR_INTITIAL_CONSUMER_GROUP,
                    RMGR_PLAN,
                    RMGR_PLAN_DIRECTIVE,
                    ROLE,
                    ROLE_GRANT,
                    ROLLBACK_SEGMENT,
                    SCHEMA_EXPORT,
                    SEQUENCE,
                    SYNONYM,
                    SYSTEM_GRANT,
                    TABLE,
                    TABLE_DATA,
                    TABLE_EXPORT,
                    TABLE_STATISTICS,
                    TABLESPACE,
                    TABLESPACE_QUOTA,
                    TRANSPORTABLE_EXPORT,
                    TRIGGER,
                    TRUSTED_DB_LINK,
                    TYPE,
                    TYPE_SPEC,
                    TYPE_BODY,
                    USER,
                    VIEW,
                    XMLSCHEMA,
                    XS_USER,
                    XS_ROLE,
                    XS_ROLESET,
                    XS_ROLE_GRANT,
                    XS_SECURITY_CLASS,
                    XS_DATA_SECURITY,
                    XS_ACL,
                    XS_ACL_PARAM,
                    XS_NAMESPACE));

    private static final HashMap<String, String> oracleObjectTypes = new HashMap<>();

    static {
        oracleObjectTypes.put(CLUSTER,CLUSTER);
        oracleObjectTypes.put(CONTEXT,CONTEXT);
        oracleObjectTypes.put(DB_LINK, WILD_DATABASE_LINK);
        oracleObjectTypes.put(DIRECTORY, DIRECTORY);
        oracleObjectTypes.put(FUNCTION, FUNCTION);
        oracleObjectTypes.put(INDEX, INDEX);
        oracleObjectTypes.put(INDEXTYPE, INDEXTYPE);
        oracleObjectTypes.put(JAVA_SOURCE, WILD_JAVA_SOURCE);
        oracleObjectTypes.put(JOB, JOB);
        oracleObjectTypes.put(LIBRARY, LIBRARY);
        oracleObjectTypes.put(MATERIALIZED_VIEW, WILD_MATERIALIZED_VIEW);
        oracleObjectTypes.put(OPERATOR, OPERATOR);
        oracleObjectTypes.put(PACKAGE_SPEC, PACKAGE);
        oracleObjectTypes.put(PACKAGE_BODY, WILD_PACKAGE_BODY);
        oracleObjectTypes.put(PROCEDURE, PROCEDURE);
        oracleObjectTypes.put(SEQUENCE, SEQUENCE);
        oracleObjectTypes.put(SYNONYM, SYNONYM);
        oracleObjectTypes.put(TABLE, TABLE);
        oracleObjectTypes.put(TRIGGER, TRIGGER);
        oracleObjectTypes.put(TYPE, TYPE);
        oracleObjectTypes.put(TYPE_BODY, WILD_TYPE_BODY);
        oracleObjectTypes.put(VIEW, VIEW);
        oracleObjectTypes.put(XMLSCHEMA, WILD_XML_SCHEMA);
    }

    /**
     * Gets oracle types.
     *
     * @return the oracle types
     */
    public static List<String> getOracleTypes() {
        return oracleTypes;
    }

    /**
     * Gets oracle object types.
     *
     * @return the oracle object types
     */
    public static List<String> getOracleObjectTypes() {
        return new ArrayList<>(oracleObjectTypes.keySet());
    }

    /**
     * Get oracle oject type from oracle type string.
     *
     * @param oracleType the oracle type
     * @return the string
     */
    public static String getOracleOjectTypeFromOracleType (String oracleType){
       return  oracleObjectTypes.get(oracleType);
    }

    /**
     * Gets oracle type from oracle oject type.
     *
     * @param oracleObjectType the oracle object type
     * @return the oracle type from oracle oject type
     */
    public static String getOracleTypeFromOracleOjectType (String oracleObjectType) {
        for (Map.Entry<String, String> entry : oracleObjectTypes.entrySet()) {
            if (entry.getValue().equals(oracleObjectType)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
