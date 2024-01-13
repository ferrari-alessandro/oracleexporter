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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type File manager.
 */
public class FileManager {
    private static final Logger logger = LogManager.getLogger(FileManager.class);

    public static final String ERROR_DURING_SAVING_FILE_S_CONTENT_IS_EMPTY = "Error during saving file: %s.Content is empty.";

    public static final String ERROR_DURING_SAVING_FILES = "Error during saving file: %s.";

    private FileManager() {}

    /**
     * Save file.
     *
     * @param file the file
     * @param clob the content of file
     */
    public static void saveFile(String file, String clob) {
        if (clob != null && !clob.isEmpty()) {
            if (logger.isDebugEnabled()) logger.debug(String.format("Saving file: %s", file));
            try (FileWriter fw = new FileWriter(file)) {
                fw.write(clob);
            } catch (IOException e) {
                logger.error(String.format(ERROR_DURING_SAVING_FILES, e.getMessage()));
            }
        }else {
            if (logger.isDebugEnabled()) logger.debug(String.format(ERROR_DURING_SAVING_FILE_S_CONTENT_IS_EMPTY,file));
        }
    }

    /**
     * Gets jar path.
     *
     * @return the jar path
     */
    protected static String getJarPath () {
        File jarPath=new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return jarPath.getParentFile().getAbsolutePath();
    }
}
