package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.RequestDao;
import dao.RequestJdbcDaoImpl;
import dao.DBUtil;
import exception.ApplicationException;
import pojo.RequestPojo;

public class RequestServiceImpl implements RequestService{

	private static final Logger logger = LogManager.getLogger(RequestServiceImpl.class);
	RequestDao requestDao;
	
	public RequestServiceImpl() {
		//this.bookDao = new BookDaoImpl();
		this.requestDao = new RequestJdbcDaoImpl();
	}
	
	@Override
	public RequestPojo createRequest(RequestPojo requestPojo) throws ApplicationException {
		logger.info("Entered addRequest() in service.");
		RequestPojo returnRequestPojo = this.requestDao.createRequest(requestPojo);
		logger.info("Exited addRequest() in service.");
		return returnRequestPojo;
	}

	@Override
	public RequestPojo updateRequest(RequestPojo requestPojo) throws ApplicationException {
		logger.info("Entered updateRequest() in service.");
		RequestPojo returnRequestPojo = this.requestDao.updateRequest(requestPojo);
		logger.info("Exited updateRequest() in service.");
		return returnRequestPojo;
	}

	@Override
	public boolean deleteRequest(int reqId) throws ApplicationException {
		logger.info("Entered deleteRequest() in service.");
		boolean returnFlag = this.requestDao.deleteRequest(reqId);
		logger.info("Exited deleteRequest() in service.");
		return returnFlag;
	}

	@Override
	public List<RequestPojo> getAllRequests() throws ApplicationException {
		logger.info("Entered getAllRequests() in service.");
		List<RequestPojo> allRequests = this.requestDao.getAllRequests();
		logger.info("Exited getAllRequests() in service.");
		return allRequests;
	}

	@Override
	public RequestPojo getARequest(int reqId) throws ApplicationException {
		logger.info("Entered getARequest() in service.");
		RequestPojo returnRequestPojo = this.requestDao.getARequest(reqId);
		logger.info("Exited getARequest() in service.");
		return returnRequestPojo;
	}

	@Override
	public List<RequestPojo> getAllPending() throws ApplicationException {
		logger.info("Entered getAllPending() in service.");
		List<RequestPojo> allPending = this.requestDao.getAllPending();
		logger.info("Exited getAllPending() in service.");
		return allPending;
	}

	public void exitApplication() {
		logger.info("Entered exitApplication() in service.");
		requestDao.exitApplication();
		logger.info("Exited exitApplication() in service.");
	}
}
