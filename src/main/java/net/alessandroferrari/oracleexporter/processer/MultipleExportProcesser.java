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

import net.alessandroferrari.oracleexporter.manager.FileManager;
import net.alessandroferrari.oracleexporter.manager.QueryManager;
import net.alessandroferrari.oracleexporter.manager.TypeToExportManager;
import net.alessandroferrari.oracleexporter.model.OracleType;
import net.alessandroferrari.oracleexporter.model.TypeToExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MultipleExportProcesser implements Processer {
    private static final Logger logger = LogManager.getLogger(MultipleExportProcesser.class);
    private Connection connection;
    private TypeToExport type;

    public MultipleExportProcesser(Connection connection, TypeToExport type) {
        this.connection=connection;
        this.type=type;
    }

    @Override
    public void execute ()  {
        ResultSet resultset;
        try {
            resultset = QueryManager.executeObjectQuery(connection, OracleType.getOracleOjectTypeFromOracleType(type.getType()));
            List<TypeToExport> result = TypeToExportManager.getTypesToExport(resultset);
            for (TypeToExport singleType: result) {
                String clob = QueryManager.executeSimpleQuery(connection, OracleType.getOracleTypeFromOracleOjectType(singleType.getType()), singleType.getName());
                FileManager.saveFile(TypeToExportManager.getFileName(type,singleType.getName()), clob);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
