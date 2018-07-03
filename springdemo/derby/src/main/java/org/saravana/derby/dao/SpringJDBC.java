package org.saravana.derby.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.saravana.derby.model.Circle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

@Component
public class SpringJDBC {
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbc;

	public int getCircleCount(){
		String sql="select count(*) from circle";
		return jdbcTemplate.queryForInt(sql);
	}
	public Circle getCircle(int circleId){
		
		String sql="select * from circle where id=?";
		Circle circle=jdbcTemplate.queryForObject(sql,new Object[]{circleId},new CircleMapper());
				
		return circle;
		
	}
	public List<Circle> getAllCircles(){
		String sql="select * from circle";
		return jdbcTemplate.query(sql, new CircleMapper());
	}
	
	public String getCircleName(int circleId){
		
		String sql="select name from circle where id=?";
		return jdbcTemplate.queryForObject(sql,new Object[] {circleId}, String.class);
	}
	
	public void insertCricle(Circle circle){
		String sql="insert into circle(id, name) values (?,?)";
		jdbcTemplate.update(sql, new Object[] {circle.getId(), circle.getName()});
		
	}
	
	public void insertCircl(Circle circle){
		String sql="insert into circle(id, name) values (:id,:name)";
		SqlParameterSource namedParameters=new MapSqlParameterSource("id",circle.getId())
				.addValue("name", circle.getName());
		namedJdbc.update(sql, namedParameters);
		
	}
	public void creatTriangleTable(){
		String sql="create Table Triangle(id INTEGER, Name VARCHAR(50))";
		jdbcTemplate.execute(sql);
	}
	
	
	private static final class CircleMapper implements RowMapper<Circle>{

		public Circle mapRow(ResultSet rs, int rownum) throws SQLException {
			// TODO Auto-generated method stub
			Circle circle=new Circle();
			circle.setId(rs.getInt("id"));
			circle.setName(rs.getString("name"));
			
			return circle;
		}

		
		
	}
	public DataSource getDataSource() {
		return dataSource;
	}
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.namedJdbc=new NamedParameterJdbcTemplate(dataSource);
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	public NamedParameterJdbcTemplate getNamedJdbc() {
		return namedJdbc;
	}
	public void setNamedJdbc(NamedParameterJdbcTemplate namedJdbc) {
		this.namedJdbc = namedJdbc;
	}
	

}
