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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.alessandroferrari.oracleexporter.model.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * The type Configuration manager.
 */
public class ConfigurationManager {
    private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);
    private static final String CONF_DIRECTORY = "conf";
    private static final String DATASOURCE_PROPERTIES_FILE = "datasource.json";

    private ConfigurationManager() {}

    /**
     * Gets data sources from file.
     *
     * @return the data sources from file
     * @throws FileNotFoundException the file not found exception
     */
    public static  List<DataSource> getDataSourcesFromFile() throws FileNotFoundException {
        String file = getDataSourceFilePath();
        if (logger.isDebugEnabled()) logger.debug(String.format("Reading file: %s", file));
        FileReader fileReader = new FileReader(file);
        return new Gson().fromJson(fileReader,new TypeToken<List<DataSource>>(){}.getType());
    }

    /**
     * Gets the path of data sources file
     *
     * @return the current path
     */
    private static String getDataSourceFilePath() {
        return FileManager.getJarPath()+File.separator+CONF_DIRECTORY+File.separator+DATASOURCE_PROPERTIES_FILE;
    }
}
