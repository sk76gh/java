package org.saravana.derby.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.saravana.derby.model.Circle;
import org.springframework.stereotype.Component;
@Component
public class JdbcDaoImpl {
	
	public Circle getCircle(int circleId){
		Connection conn=null;
		Circle circle=null;
		try {
			String driver = "org.apache.derby.jdbc.ClientDriver";
			Class.forName(driver).newInstance();
			
			conn=DriverManager.getConnection("jdbc:derby://localhost:1527/derbydb");
			
			PreparedStatement ps=conn.prepareStatement("select * from circle where id=?");
			ps.setInt(1, circleId);
			
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				circle=new Circle(circleId,rs.getString("name"));
			}
			
			rs.close();
			ps.close();
				
		return circle;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return circle;
	}

}
