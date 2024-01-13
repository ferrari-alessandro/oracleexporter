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

import net.alessandroferrari.oracleexporter.commandline.OracleExporterOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProcesserFactory {
    private static final Logger logger = LogManager.getLogger(ProcesserFactory.class);

    private ProcesserFactory() {}

    public static Processer getProcesser(String[] args) {
        if( args.length > 0) {
            CommandLineParser parser = new DefaultParser();
            try {
                CommandLine commandLine = parser.parse(OracleExporterOptions.getOptions(), args);
                String importtype = commandLine.getOptionValue(OracleExporterOptions.IMPORTTYPE);
                if(importtype.equals(OracleExporterOptions.COMMANDLINE)){
                    return new CommandLineProcesser(commandLine);
                }else if(importtype.equals(OracleExporterOptions.CONFIG)){
                    return new OracleObjectProcesser();
                }else {
                    return new HelpProcesser();
                }
            } catch (Exception e) {
                logger.error(String.format("%s", e.getMessage()));
            }
        }
        return new HelpProcesser();
    }
}
