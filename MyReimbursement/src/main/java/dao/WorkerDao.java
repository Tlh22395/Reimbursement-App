package dao;

import java.util.List;

import exception.ApplicationException;
import pojo.WorkerPojo;

public interface WorkerDao {
	List<WorkerPojo> getAllWorkers() throws ApplicationException;
	WorkerPojo getWorkerById(int workerId) throws ApplicationException;
	WorkerPojo createWorker(WorkerPojo workerPojo) throws ApplicationException;
	WorkerPojo updateWorker(WorkerPojo workerPojo) throws ApplicationException;
	void exitApplication();
	
}
