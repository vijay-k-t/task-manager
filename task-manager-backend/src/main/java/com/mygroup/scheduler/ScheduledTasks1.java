package com.mygroup.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.Timestamp;    

import com.mygroup.service.TaskService;
import com.mygroup.data.Task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.SchedulingTaskExecutor;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks1 {

	private static final Logger log = LoggerFactory.getLogger(ScheduledTasks1.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Autowired
	TaskService service;

	@Scheduled(fixedRate = 100000)
	//@Scheduled(cron = "0 0 18 * * ?")
	public void reportCurrentTime()  throws Exception {
		//Thread.sleep(10000);
		log.info("The time is now {}", dateFormat.format(new Date()));
		Task task = new Task();
		task.setAssigneeId("47a0f5be-4c56-46e5-9f8c-e13ddd8d301f");
		task.setDescription("Test description");
		Date date = new Date();  
        Timestamp ts=new Timestamp(date.getTime());  
		task.setDue_date(ts);
		task.setName("Task scheduled at " + dateFormat.format(new Date()));
		task.setProjectId("413d6919-aaca-4774-b229-0106d5b95a29");
		task.setTasklistId("d5bcf4b7-c123-49e2-b4ac-241a2f1dc334");
		task.setCreated_at(new Timestamp(new Date().getTime()));
		service.save(task);
	}
}