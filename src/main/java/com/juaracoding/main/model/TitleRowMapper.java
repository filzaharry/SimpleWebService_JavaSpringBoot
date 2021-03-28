package com.juaracoding.main.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class TitleRowMapper implements RowMapper<Title>{
	
	@Override
	public Title mapRow(ResultSet rs, int rowNum) throws SQLException{
		
		Title title = new Title();
		title.setAffected_data(rs.getString("affected_data"));
		title.setWorker_ref_id(rs.getInt("worker_ref_id"));
		title.setWorker_title(rs.getString("worker_title"));
		
		return title;
	}

}
