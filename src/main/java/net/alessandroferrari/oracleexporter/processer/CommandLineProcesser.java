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
import net.alessandroferrari.oracleexporter.manager.DataSourceManager;
import net.alessandroferrari.oracleexporter.manager.TypeToExportManager;
import net.alessandroferrari.oracleexporter.model.TypeToExport;
import org.apache.commons.cli.CommandLine;

import java.sql.Connection;
import java.sql.SQLException;


public class CommandLineProcesser implements Processer {
    private String datasource;
    private String user;
    private String password;
    private String oracletype;
    private String oracletypename;
    private String outputdestination;

    /**
     * Instantiates a new Command line export processer.
     *
     * @param commandLine the command line
     */
    public CommandLineProcesser(CommandLine commandLine) {
        this.datasource = commandLine.getOptionValue("datasource");
        this.user = commandLine.getOptionValue("user");
        this.password = commandLine.getOptionValue("password");
        this.oracletype= commandLine.getOptionValue("oracletype");
        this.oracletypename= commandLine.getOptionValue("oracletypename");
        this.outputdestination= commandLine.getOptionValue("outputdestination");
    }

    public void execute () throws InvalidParametersException, SQLException {
        if(!validateDataSource()) {
            throw new InvalidParametersException("Invalid command line parameters value.");
        }
        Connection connection = DataSourceManager.getConnection(this.datasource, this.user, this.password);
        TypeToExport typeToExport = new TypeToExport(this.oracletype, this.oracletypename, this.outputdestination);
        if (TypeToExportManager.validateParams(typeToExport)) {
            if(TypeToExportManager.isSimpleExport(typeToExport)) {
                new SingleExportProcesser(connection, typeToExport).execute();
            }else {
                new MultipleExportProcesser(connection, typeToExport).execute();
            }
        } else {
            throw new InvalidParametersException("Invalid parameters for "+typeToExport);
        }
    }
    private boolean validateDataSource() {
        return !datasource.isEmpty() && !user.isEmpty()&&!password.isEmpty()&&!oracletype.isEmpty()&&
                !oracletypename.isEmpty()&&!outputdestination.isEmpty();
    }
}
