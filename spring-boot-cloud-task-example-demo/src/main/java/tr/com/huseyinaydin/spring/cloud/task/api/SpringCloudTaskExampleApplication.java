package tr.com.huseyinaydin.spring.cloud.task.api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;

/**
 * 
 * @author Huseyin_Aydin
 * @since 1994
 * @category Spring Boot Examples
 * 
 */

@SpringBootApplication
@EnableTask
public class SpringCloudTaskExampleApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudTaskExampleApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Welcome to " + args[0]);
	}

	@BeforeTask
	public void start(TaskExecution taskExecution) {
		System.out.println("TaskName : " + taskExecution.getTaskName() + " Execution Id : "
				+ taskExecution.getExecutionId() + " started...");
	}

	@AfterTask
	public void end(TaskExecution taskExecution) {
		System.out.println("TaskName : " + taskExecution.getTaskName() + " Execution Id : "
				+ taskExecution.getExecutionId() + " completed...");
	}

	@FailedTask
	public void fail(TaskExecution taskExecution, Throwable throwable) {
		System.out.println("TaskName : " + taskExecution.getTaskName() + " Execution Id : "
				+ taskExecution.getExecutionId() + " failed...");
	}
}