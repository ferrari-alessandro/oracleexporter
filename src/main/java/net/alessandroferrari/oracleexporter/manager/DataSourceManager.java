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

import net.alessandroferrari.oracleexporter.model.DataSource;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Data source manager.
 */
public class DataSourceManager {
    private static final Logger logger = LogManager.getLogger(DataSourceManager.class);
    private DataSourceManager () {}

    /**
     * Gets connections.
     *
     * @param dataSources the data sources
     * @return the connections
     * @throws SQLException the sql exception
     */
    public static Map<DataSource,Connection> getConnections(List<DataSource> dataSources) throws SQLException {
        HashMap<DataSource,Connection> connections = new HashMap<>();
            for (DataSource dataSource: dataSources) {
                if (dataSource.getEnabled().booleanValue()) {
                    if (logger.isDebugEnabled()) logger.debug(String.format("Datasource creation: %s", dataSource));
                    OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
                    ocpds.setLoginTimeout(10);
                    ocpds.setURL(dataSource.getUrl());
                    ocpds.setUser(dataSource.getUser());
                    ocpds.setPassword(dataSource.getPassword());
                    Connection conn = ocpds.getConnection();
                    connections.put(dataSource, conn);
                }
            }
         return connections;
    }

    /**
     * Gets connection.
     *
     * @param datasource the datasource
     * @param user       the user
     * @param password   the password
     * @return the connection
     * @throws SQLException the sql exception
     */
    public static Connection getConnection(String datasource, String user, String password) throws SQLException {
        OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource();
        ocpds.setURL(datasource);
        ocpds.setUser(user);
        ocpds.setPassword(password);
        return ocpds.getConnection();
   }
}
