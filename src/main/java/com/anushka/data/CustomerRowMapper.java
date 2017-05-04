package com.anushka.data;

import com.anushka.entity.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Created by rxd2095 on 5/3/17.
 */
public class CustomerRowMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long custId = rs.getLong("ID");
        String firstName = rs.getString("FIRST_NAME");
        String lastName = rs.getString("LAST_NAME");
        LocalDate birthDay = rs.getDate("BIRTH_DAY").toLocalDate();
        return new Customer(custId, firstName, lastName, birthDay);
    }
}
