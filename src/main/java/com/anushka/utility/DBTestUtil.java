package com.anushka.utility;

import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxd2095 on 3/20/17.
 */
public class DBTestUtil {

    private DBTestUtil() {
    }

    public static void resetAutoIncrementColumns(String prop, ApplicationContext applicationContext,
                                                 String... tableNames) throws SQLException {
        DataSource dataSource = applicationContext.getBean(DataSource.class);
        List<String> resetSqlTemplate = new ArrayList<>();
        resetSqlTemplate.add(getResetSqlTemplate(applicationContext, prop));
        try (Connection dbConnection = dataSource.getConnection()) {
            //Create SQL statements that reset the auto increment columns and invoke
            //the created SQL statements.
            for (String resetSqlArgument: tableNames) {
                try (Statement statement = dbConnection.createStatement()) {
                    for (String template : resetSqlTemplate) {
                        String resetSql = String.format(template, resetSqlArgument);
                        statement.execute(resetSql);
                    }
                }
            }
        }
    }

    private static String getResetSqlTemplate(ApplicationContext applicationContext, String property) {
        //Read the SQL template from the properties file
        Environment environment = applicationContext.getBean(Environment.class);
        return environment.getRequiredProperty(property);
    }

}
