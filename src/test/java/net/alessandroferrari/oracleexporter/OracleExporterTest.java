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

import static org.junit.Assert.assertTrue;

import net.alessandroferrari.oracleexporter.exception.InvalidParametersException;
import net.alessandroferrari.oracleexporter.processer.ProcesserFactory;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/**
 * The type Oracle exporter test.
 */
public class OracleExporterTest {
    public static final String I_CONFIG = "-i config";

    /**
     * Test help.
     */
    @Test
    public void testHelp() throws SQLException, InvalidParametersException, IOException {
        String [] args = new String[0];
        ProcesserFactory.getProcesser(args).execute();
        assertTrue( true );
    }

    /**
     * Test help with invalid import type.
     */
    @Test
    public void testHelpWithInvalidImportType() throws SQLException, InvalidParametersException, IOException {
        String command = "-i notvalid";
        String [] args = command.split(" ");
        ProcesserFactory.getProcesser(args).execute();
        assertTrue( true );
    }

    /**
     * Test by config.
     */
    @Test
    public void testByConfig() throws SQLException, InvalidParametersException, IOException {
        deleteOutPutDir(getCurrentPath());
        String command = I_CONFIG;
        String [] args = command.split(" ");
        ProcesserFactory.getProcesser(args).execute();
        File f = new File (getCurrentPath() +"/"+"PKG_AC_ACCOUNT.pkb");
        assertTrue(f.exists() && f.isFile() && f.length() > 0);
    }

    /**
     * Test wildcard by config.
     */
    @Test
    public void testWildcardByConfig() throws SQLException, InvalidParametersException, IOException {
        deleteOutPutDir(getCurrentPath());
        String command = I_CONFIG;
        String [] args = command.split(" ");
        ProcesserFactory.getProcesser(args).execute();
        File f = new File (getCurrentPath() +"/"+"PKG_AC_ACCOUNT.pks");
        assertTrue(f.exists() && f.isFile() && f.length() > 0);
    }

    /**
     * Test by command line.
     */
    @Test
    public void testByCommandLine() throws SQLException, InvalidParametersException, IOException {
        deleteOutPutDir(getCurrentPath());
        String [] args = getCommand(false);
        ProcesserFactory.getProcesser(args).execute();
        File f = new File (args[13]);
        assertTrue(f.exists() && f.isFile() && f.length() > 0);
    }

    /**
     * Test wildcard by command line.
     */
    @Test
    public void testWildcardByCommandLine() throws SQLException, InvalidParametersException, IOException {
        deleteOutPutDir(getCurrentPath());
        String [] args = getCommand(true);
        ProcesserFactory.getProcesser(args).execute();
        File f = new File (getCurrentPath() +"/"+"PKG_AC_ACCOUNT_PACKAGE_SPEC.sql");
        assertTrue(f.exists() && f.isFile() && f.length() > 0);
    }

    private static String getCurrentPath() {
        return System.getProperty("user.dir")+"/target";
    }

    private static  String [] getCommand(boolean wildcard) {
         String result = "-i commandline ";
         result += "-d jdbc:oracle:thin:@localhost:1521:xe ";
         result += "-u TEST ";
         result += "-p TEST ";
         if(wildcard) {
             result += "-o PACKAGE_SPEC ";
             result += "-n * ";
             result += "-f "+ getCurrentPath();
         }else {
             result += "-o PACKAGE_BODY ";
             result += "-n PKG_AC_ACCOUNT ";
             result += "-f "+ getCurrentPath()+"/PKG_AC_ACCOUNT.pkb";
         }
        return result.split(" ");
    }

    private void deleteOutPutDir(String tempDir) {
        File d = new File (tempDir);
        for(File file: d.listFiles())
            if (!file.isDirectory())
                file.delete();
    }
}
