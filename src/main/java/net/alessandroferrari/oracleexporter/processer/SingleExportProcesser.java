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
import net.alessandroferrari.oracleexporter.model.TypeToExport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class SingleExportProcesser implements Processer {
    private static final Logger logger = LogManager.getLogger(SingleExportProcesser.class);
    private Connection connection;
    private TypeToExport type;
    public SingleExportProcesser(Connection connection, TypeToExport type) {
        this.connection=connection;
        this.type=type;
    }

    @Override
    public void execute ()  {
        String clob = null;
        try {
            clob = QueryManager.executeSimpleQuery(connection, type.getType(), type.getName());
            FileManager.saveFile(TypeToExportManager.getFileName(type,null), clob);
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
