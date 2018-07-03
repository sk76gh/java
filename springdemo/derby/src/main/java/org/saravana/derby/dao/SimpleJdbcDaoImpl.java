package org.saravana.derby.dao;

import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class SimpleJdbcDaoImpl extends SimpleJdbcDaoSupport {
	

	public int getCircleCount(){
		String sql="select count(*) from circle";
		return this.getJdbcTemplate().queryForInt(sql);
	}

}
