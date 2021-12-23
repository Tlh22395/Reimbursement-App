package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.RequestPojo;

public interface RequestDao {
	List<RequestPojo> getAllRequests() throws ApplicationException;
	List<RequestPojo> getAllPending() throws ApplicationException;
	RequestPojo getARequest(int reqId) throws ApplicationException;
	RequestPojo createRequest(RequestPojo requestPojo) throws ApplicationException;
	RequestPojo updateRequest(RequestPojo requestPojo) throws ApplicationException;
	boolean deleteRequest(int reqId) throws ApplicationException;
	void exitApplication();
	
}
