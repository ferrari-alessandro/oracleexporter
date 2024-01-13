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
package net.alessandroferrari.oracleexporter.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.*;

/**
 * The type Query manager.
 */
public class QueryManager {
    private static final Logger logger = LogManager.getLogger(QueryManager.class);
    private static final String DDL_QUERY = "select dbms_metadata.get_ddl('PARAM1','PARAM2',USER) from dual";
    private static final String USER_OBJECTS_QUERY = "SELECT OBJECT_TYPE,OBJECT_NAME FROM USER_OBJECTS WHERE OBJECT_TYPE = 'PARAM1'";
    private static final String PARAM1 = "PARAM1";
    private static final String PARAM2 = "PARAM2";

    private QueryManager(){}

    /**
     * Gets simple query.
     *
     * @param type the tipo
     * @param name the name
     * @return the simple query
     */
    protected static String getSimpleQuery(String type, String name) {
        return DDL_QUERY.replace(PARAM1, type).replace(PARAM2, name);
    }

    /**
     * Gets object query.
     *
     * @param type the type
     * @return the object query
     */
    protected static String getObjectQuery(String type) {
        return USER_OBJECTS_QUERY.replace(PARAM1, type);
    }

    /**
     * Execute object query result set.
     *
     * @param connection the connection
     * @param type       the type
     * @return the result set
     * @throws SQLException the sql exception
     */
    public static ResultSet executeObjectQuery(Connection connection, String type) throws SQLException {
        String query = QueryManager.getObjectQuery(type);
        if (logger.isDebugEnabled()) logger.debug(String.format("Datasource: %s, query: %s" ,connection.getSchema(),query));
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    /**
     * Execute simple query string.
     *
     * @param connection the connection
     * @param type       the type
     * @param name       the name
     * @return the string
     * @throws SQLException the sql exception
     */
    public static String executeSimpleQuery(Connection connection, String type, String name) throws SQLException {
        String query = getSimpleQuery(type,name);
        if (logger.isDebugEnabled()) logger.debug(String.format("Datasource: %s, query: %s" ,connection.getSchema(),query));
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                Clob clobValue = rs.getClob(1);
                return clobToString(clobValue);
            }
        } catch (SQLException | IOException e) {
            logger.error(String.format("%s", e.getMessage()));
        }
        return "";
    }

    /**
     * Convert Clob to String.
     *
     * @param clob
     * @return
     * @throws SQLException
     * @throws IOException
     */
    private static String clobToString(java.sql.Clob clob) throws SQLException, IOException {
        final StringBuilder sb = new StringBuilder();
            final Reader reader = clob.getCharacterStream();
            final BufferedReader br = new BufferedReader(reader);
            int buffer;
            while(-1 != (buffer = br.read())) {
                sb.append((char)buffer);
            }
            br.close();
        return sb.toString();
    }
}
