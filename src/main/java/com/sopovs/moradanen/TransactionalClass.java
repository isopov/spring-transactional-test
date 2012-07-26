package com.sopovs.moradanen;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

@Named
@Singleton
@Transactional
public class TransactionalClass {

	private JdbcTemplate jdbcTemplate;

	@Inject
	public void setJdbcTemplate(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void prepareData() {
		jdbcTemplate.execute("create table foo(id int, val varchar(100))");
	}

	public List<String> getData() {
		return jdbcTemplate.query("select val from foo", new RowMapper<String>() {

			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("val");
			}
		});
	}

	public void insertSimple() {
		jdbcTemplate.execute("insert into foo(id, val) values (1, 'test')");
	}

	@Transactional
	public void insertWithException() {
		jdbcTemplate.execute("insert into foo(id, val) values (1, 'test')");
		throw new RuntimeException("Dummy exception");
	}

}
