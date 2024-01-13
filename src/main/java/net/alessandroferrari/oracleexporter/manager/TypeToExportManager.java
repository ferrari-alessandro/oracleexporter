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

import net.alessandroferrari.oracleexporter.commandline.OracleExporterOptions;
import net.alessandroferrari.oracleexporter.exception.InvalidParametersException;
import net.alessandroferrari.oracleexporter.model.OracleType;
import net.alessandroferrari.oracleexporter.model.TypeToExport;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Type to export manager.
 */
public class TypeToExportManager {


    private static final String DOT = ".";

    private TypeToExportManager (){}

    /**
     * Validate input params.
     *
     * @param type the type
     * @return the boolean
     * @throws InvalidParametersException the invalid parameters exception
     */
    public static boolean validateParams(TypeToExport type) throws InvalidParametersException {
        if(type.getType() == null || type.getType().isEmpty() || type.getPath().isEmpty() || type.getName().isEmpty()) {
            throw new InvalidParametersException("Missing values.Invalid object type: "+ type);
        }
        if (type.getName().equals(OracleExporterOptions.WILDCARD)) {
            if (!OracleType.getOracleObjectTypes().contains(type.getType())) {
                throw new InvalidParametersException("Invalid object type for wildcard.Valid types are: " + OracleType.getOracleObjectTypes());
            }
            if (type.getPath().contains(DOT)) {
                throw new InvalidParametersException("Invalid path for wildcard.Specify a valid path for "+type);
            }
        }else {
            if(!OracleType.getOracleTypes().contains(type.getType())) {
                throw new InvalidParametersException("Invalid object type.Valid types are: " + OracleType.getOracleTypes().toString());
            }
            if (!type.getPath().contains(DOT)) {
                throw new InvalidParametersException("Invalid path.Specify a single file name for "+type);
            }
        }

        return true;
    }

    /**
     * Is simple export boolean.
     *
     * @param type the type
     * @return the boolean
     */
    public static boolean isSimpleExport(TypeToExport type) {
        return OracleType.getOracleTypes().contains(type.getType()) && !type.getName().equals(OracleExporterOptions.WILDCARD);
    }

    /**
     * Gets types to export.
     *
     * @param rs the rs
     * @return the types to export
     * @throws SQLException the sql exception
     */
    public static List<TypeToExport> getTypesToExport(ResultSet rs) throws SQLException {
        List<TypeToExport> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new TypeToExport(rs.getString(1), rs.getString(2), ""));
            }
        return result;
    }
    /**
     * Gets file name.
     *
     * @param typeToExport the type to export
     * @param singleType   the single type
     * @return the file name
     */
    public static String getFileName(TypeToExport typeToExport, String singleType) {
        if (typeToExport.getPath().contains(".")){
            return typeToExport.getPath();
        }else {
            return typeToExport.getPath()+ File.separator + singleType+"_"+ typeToExport.getType()+ ".sql";
        }
    }
}
