package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.WorkerDao;
import dao.WorkerJdbcDaoImpl;
import dao.DBUtil;
import exception.ApplicationException;
import pojo.WorkerPojo;

public class WorkerServiceImpl implements WorkerService {

	private static final Logger logger = LogManager.getLogger(WorkerServiceImpl.class);
	
	WorkerDao workerDao;
	
	public WorkerServiceImpl() {
	
		this.workerDao = new WorkerJdbcDaoImpl();
	}
	@Override
	public WorkerPojo createWorker(WorkerPojo workerPojo) throws ApplicationException {
		logger.info("Entered createWorker() in service.");
		WorkerPojo returnWorkerPojo = this.workerDao.createWorker(workerPojo);
		logger.info("Exited createWorker() in service.");
		return returnWorkerPojo;
	}

	@Override
	public WorkerPojo updateWorker(WorkerPojo workerPojo) throws ApplicationException {
		logger.info("Entered updateWorker() in service.");
		WorkerPojo returnWorkerPojo = this.workerDao.updateWorker(workerPojo);
		logger.info("Exited updateWorker() in service.");
		return returnWorkerPojo;
	}
	
	@Override
	public List<WorkerPojo> getAllWorkers() throws ApplicationException {
		logger.info("Entered getAllWorkers() in service.");
		List<WorkerPojo> allWorkers = this.workerDao.getAllWorkers();
		logger.info("Exited getAllWorkers() in service.");
		return allWorkers;
	}

	@Override
	public WorkerPojo getWorkerById(int workerId) throws ApplicationException {
		logger.info("Entered getAWorker() in service.");
		WorkerPojo returnWorkerPojo = this.workerDao.getWorkerById(workerId);
		logger.info("Exited getAWorker() in service.");
		return returnWorkerPojo;
	}

	@Override
	public void exitApplication() {
		logger.info("Entered exitApplication() in service.");
		workerDao.exitApplication();
		logger.info("Exited exitApplication() in service.");
	}
	
	
}
