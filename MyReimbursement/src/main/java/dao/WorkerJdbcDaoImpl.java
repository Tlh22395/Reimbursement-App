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
import pojo.WorkerPojo;

public class WorkerJdbcDaoImpl implements WorkerDao {
	private static final Logger logger = LogManager.getLogger(WorkerJdbcDaoImpl.class);
	
	@Override
	public WorkerPojo createWorker(WorkerPojo workerPojo) throws ApplicationException {
		logger.info("Entered createWorker() in dao.");
		
		// this bookPojo does not have a book id set in it.
		//set the book_removed to false
		
		try {
			// jdbc steps 3 and 4
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
//			String query = "insert into book_details(book_title, book_author, book_genre, book_cost, book_removed)" 
//							+ "values('"+bookPojo.getBookTitle()+"','"+bookPojo.getBookAuthor()
//							+"','"+bookPojo.getBookGenre()+"',"+bookPojo.getBookCost()
//							+","+bookPojo.isBookRemoved()+")";
//			
//			int rowsAffected = stmt.executeUpdate(query);
//			if(rowsAffected != 0) { // means the record got inserted successfully
//				// take out the primary key and store in the bookPojo object
//				bookPojo.setBookId(1);// hard coded to 1 - but later will figure out to fetch the generated
//										// primary key from DB
//			}
			
			// fixed the code to return the generated book_id
			String query = "insert into worker(first_name, last_name, username, password, title, state)" 
					+ "values('"+workerPojo.getFirstName()+"','"+workerPojo.getLastName()
					+"','"+workerPojo.getUsername()+"',"+workerPojo.getPassword()
					+","+workerPojo.getTitle()+",'"+workerPojo.getState()+"') returning worker_id";
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
			workerPojo.setWorkerId(rs.getInt(1));
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		
		logger.info("Exited createWorker() in dao.");
		return workerPojo;
	}
	
	@Override
	public WorkerPojo updateWorker(WorkerPojo workerPojo) throws ApplicationException {
		logger.info("Entered updateWorker() in dao.");
		
		try {
			// jdbc step 3 and 4
			Connection conn = DBUtil.makeConnection();
			Statement stmt = conn.createStatement();
			String query = "update worker set title="+workerPojo.getTitle()
							+" where worker_id="+workerPojo.getWorkerId();

			int rowsAffected = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());		
		}
		
		logger.info("Exited updateWorker() in dao.");
		return workerPojo;
	}
	@Override
	public List<WorkerPojo> getAllWorkers() throws ApplicationException {
		logger.info("Entered getAllWorkers() in dao.");
		
		// create a empty collection which is going to hold all the records from the DB
		// as pojo Object
		List<WorkerPojo> allWorkers = new ArrayList<WorkerPojo>();

		Statement stmt;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from worker where worker_id >= 1";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				// here as we iterate through the rs we should
				// each record in a pojo object and
				// add it to the collection
				// and at the end we return the collection

				// as we iterate we are taking each record and storing it in a bookPojo object
				WorkerPojo workerPojo = new WorkerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));

				// add the bookPojo obj to a collection
				allWorkers.add(workerPojo);

			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getAllWorkers() in dao.");
		return allWorkers;
	}
	@Override
	public WorkerPojo getWorkerById(int workerId) throws ApplicationException {
		logger.info("Entered getAWorker() in dao.");
		
		Statement stmt;
		WorkerPojo workerPojo = null;
		try {
			Connection conn = DBUtil.makeConnection();
			stmt = conn.createStatement();
			String query = "select * from worker where worker_id="+workerId
							+ "and worker_id >= 1";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				workerPojo = new WorkerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			throw new ApplicationException(e.getMessage());
		}
		logger.info("Exited getABook() in dao.");
		return workerPojo;
	}

	@Override
	public void exitApplication() {
		logger.info("Entered exitApplication() in dao.");
		DBUtil.closeConnection();
		logger.info("Exited exitApplication() in dao.");
	}
	
	

}
