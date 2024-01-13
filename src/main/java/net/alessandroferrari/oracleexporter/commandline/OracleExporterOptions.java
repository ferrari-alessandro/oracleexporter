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
package net.alessandroferrari.oracleexporter.commandline;

import org.apache.commons.cli.Options;

/**
 * The type Oracle exporter options.
 */
public class OracleExporterOptions {

    public static final String IMPORTTYPE = "importtype";
    public static final String DATASOURCE = "datasource";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String ORACLETYPE = "oracletype";
    public static final String ORACLETYPENAME = "oracletypename";
    public static final String OUTPUTDESTINATION = "outputdestination";
    public static final String COMMANDLINE = "commandline";
    public static final String CONFIG = "config";
    public static final String WILDCARD = "*";

    private OracleExporterOptions() {}

    /**
     * Gets options.
     *
     * @return the options
     */
    public static Options getOptions () {
        Options options = new Options();
        options.addOption("i", IMPORTTYPE, true, "Specify commandline or config.");
        options.addOption("d", DATASOURCE, true, "Specify datasource url.");
        options.addOption("u", USER, true, "Specify database schema user.");
        options.addOption("p", PASSWORD, true, "Specify schema password.");
        options.addOption("o", ORACLETYPE, true, "Specify a valid oracletype (es.PACKAGE_BODY).");
        options.addOption("n", ORACLETYPENAME, true, "Specify a single name or * for get all.");
        options.addOption("f", OUTPUTDESTINATION, true, "Specify a file name or directory.");
        return options;
    }
}

