package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.postgresql.util.PSQLException;

import exception.ApplicationException;
import pojo.RequestPojo;

public class RequestJdbcDaoImpl implements RequestDao {
	private static final Logger logger = LogManager.getLogger(RequestJdbcDaoImpl.class);
	
	@Override
	public RequestPojo createRequest(RequestPojo requestPojo) throws ApplicationException {
		logger.info("Entered createRequest() in dao.");

		
		try {
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			
			String query = "insert into request(amount, description, status, resolved_by)" 
					+ "values('"+requestPojo.getAmount()+"','"+requestPojo.getDescription()
					+"','"+requestPojo.getStatus()+"',"+requestPojo.getResolvedBy()+"') returning book_id";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			requestPojo.setReqId(rs.getInt(1));
			
		}catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited createRequest() in dao.");
		return requestPojo;
		}
	
	@Override
	public RequestPojo updateRequest(RequestPojo requestPojo) throws ApplicationException {
		logger.info("Entered updateRequest() in dao.");
		
		try {
			// jdbc step 3 and 4
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "update request set status="+requestPojo.getStatus()
							+" where req_id="+requestPojo.getReqId();

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());		
		}
		
		logger.info("Exited updateRequest() in dao.");
		return requestPojo;
		
	}
	
	@Override
	public boolean deleteRequest(int reqId) throws ApplicationException {
		logger.info("Entered deleteRequest() in dao.");
		
		boolean flag = true;
		
		int rowsAffected = 0;
		try {
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			// here we are not going to do a hard delete, we are going 
			// for a soft delete.
			String query = "update request set status=true where req_id="+reqId;
			rowsAffected = stmt.executeUpdate(query);
			System.out.println(rowsAffected);
			
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		if(rowsAffected == 0)
			flag = false;
		
		logger.info("Exited deleteRequest() in dao.");
		return flag;
	}
	
	@Override
	public List<RequestPojo> getAllRequests() throws ApplicationException {
		logger.info("Entered getAllRequests() in dao.");
		
		// create a empty collection which is going to hold all the records from the DB
		// as pojo Object
		List<RequestPojo> allRequests = new ArrayList<RequestPojo>();

		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from request where req_id >=1 ";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// here as we iterate through the rs we should
				// each record in a pojo object and
				// add it to the collection
				// and at the end we return the collection

				// as we iterate we are taking each record and storing it in a bookPojo object
				RequestPojo requestPojo = new RequestPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getString(6));

				// add the bookPojo obj to a collection
				allRequests.add(requestPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllRequests() in dao.");
		return allRequests;
	}

	@Override
	public RequestPojo getARequest(int reqId) throws ApplicationException {
		logger.info("Entered getARequest() in dao.");
		
		Statement stmt;
		RequestPojo requestPojo = null;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from request where req_id="+reqId
							+ "and status=PENDING";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				requestPojo = new RequestPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getString(6));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getARequest() in dao.");
		return requestPojo;
	}

	@Override
	public void exitApplication() {
		logger.info("Entered exitApplication() in dao.");
		DBUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");

	}

	@Override
	public List<RequestPojo> getAllPending() throws ApplicationException {
logger.info("Entered getAllPending() in dao.");
		
		// create a empty collection which is going to hold all the records from the DB
		// as pojo Object
		List<RequestPojo> allPending = new ArrayList<RequestPojo>();

		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from request where status = PENDING ";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// here as we iterate through the rs we should
				// each record in a pojo object and
				// add it to the collection
				// and at the end we return the collection

				// as we iterate we are taking each record and storing it in a bookPojo object
				RequestPojo requestPojo = new RequestPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getString(4),
						rs.getString(5), rs.getString(6));

				// add the bookPojo obj to a collection
				allPending.add(requestPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllPending() in dao.");
		return allPending;
		
	}
}
