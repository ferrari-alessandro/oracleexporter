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
package net.alessandroferrari.oracleexporter.processer;

import net.alessandroferrari.oracleexporter.exception.InvalidParametersException;
import net.alessandroferrari.oracleexporter.manager.ConfigurationManager;
import net.alessandroferrari.oracleexporter.manager.DataSourceManager;
import net.alessandroferrari.oracleexporter.manager.TypeToExportManager;
import net.alessandroferrari.oracleexporter.model.DataSource;
import net.alessandroferrari.oracleexporter.model.TypeToExport;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OracleObjectProcesser implements Processer {

    @Override
    public void execute () throws SQLException, IOException, InvalidParametersException {
        List<DataSource> dataSourcesFromFile = ConfigurationManager.getDataSourcesFromFile();
        Map<DataSource, Connection> connections = DataSourceManager.getConnections(dataSourcesFromFile);
        Set<DataSource> datasources = connections.keySet();
        for (DataSource datasource: datasources) {
            Connection connection = connections.get(datasource);
            exportFromListType(connection, datasource.getExports());
        }
    }

    /**
     *
     * @param connection
     * @param typeToExport
     * @throws InvalidParametersException
     * @throws SQLException
     */
    private static void exportFromListType(Connection connection, List<TypeToExport> typeToExport) throws InvalidParametersException {
        for (TypeToExport type: typeToExport) {
            if(TypeToExportManager.validateParams(type)) {
                if(TypeToExportManager.isSimpleExport(type)) {
                   new SingleExportProcesser(connection, type).execute();
                }else {
                    new MultipleExportProcesser(connection, type).execute();
                }
            } else {
                throw new InvalidParametersException("Invalid parameters for "+type);
            }
        }
    }
}

