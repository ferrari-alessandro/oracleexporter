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
package net.alessandroferrari.oracleexporter;

import net.alessandroferrari.oracleexporter.processer.ProcesserFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The type Oracle exporter.
 */
public class OracleExporter {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    private static final Logger logger = LogManager.getLogger(OracleExporter.class);

    public static void main( String[] args )  {
        try {
            ProcesserFactory.getProcesser(args).execute();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}


