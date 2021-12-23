import java.time.LocalDate;

import org.postgresql.util.PSQLException;

import exception.ApplicationException;
import io.javalin.Javalin;
import pojo.WorkerPojo;
import pojo.ErrorPojo;
import service.WorkerService;
import service.WorkerServiceImpl;


public class WorkerMain {
public static void main(String[] args) {
		
		WorkerService workerService = new WorkerServiceImpl();
		Javalin server = Javalin.create((config) -> config.enableCorsForAllOrigins()).start(4040);
		
		//pathParam - http://localhost:4040/api/books/5/Comedy
		//queryParam - http://localhost:4040/api/books/bookId=5&bookGenre=Comedy
		
		//http://localhost:4040/hello
		server.get("hello", (ctx) -> {
			// tell here what to do when the hello endpoint is requested for
			System.out.println("Hello endpoint is requested!!");
			ctx.result("Hello endpoint is requested!!");
		});
		
		// get endpoint to fetch all the books
				// http://localhost:4040/api/books
				server.get("api/workers" , (ctx) -> {
					ctx.json(workerService.getAllWorkers());
					//System.out.println("get all books!");
		});
				
				// get endpoint to fetch one book
				// http://localhost:4040/api/books/5
				// 5 is a pathParam and they are given in a {}
				server.get("api/workers/{wid}", (ctx) -> {
					ctx.json(workerService.getWorkerById(Integer.parseInt(ctx.pathParam("wid"))));
		});
				
				// post endpoint to add a book
				// http://localhost:4040/api/books
				server.post("api/workers", (ctx) -> {
					WorkerPojo returnWorkerPojo = workerService.createWorker(ctx.bodyAsClass(WorkerPojo.class));
					ctx.json(returnWorkerPojo);
		});
				
				// put endpoint to update a book
				// http://localhost:4040/api/books
				server.put("api/workers/{wid}", (ctx) -> {
					WorkerPojo returnBookPojo = workerService.updateWorker(ctx.bodyAsClass(WorkerPojo.class));
					ctx.json(returnBookPojo);
		});
				
				server.exception(ApplicationException.class, (ae, ctx) -> {
					ErrorPojo error = new ErrorPojo();
					error.setErrorMessage(ae.getMessage());
					ctx.json(error).status(500);
		});
				
		
	}
}