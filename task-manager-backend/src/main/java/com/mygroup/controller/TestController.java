package com.mygroup.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygroup.data.Test;
import com.mygroup.scheduler.ScheduledTasks1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class TestController {

    @Autowired
	JdbcTemplate jdbcTemplate;
    
    @GetMapping("/appName")
    public String getAppName() {
        return "Hello, This is SpringBoot Second Project Application";
    }

    @GetMapping("/query/{id}")
	public Test query(@PathVariable("id") String id) {
		System.out.println(id);
		List<Map<String, Object>> testobj = jdbcTemplate.query("select * from TEST_1", new ColumnMapRowMapper(),new Object[] {});
		System.out.println(testobj);
		Test result = jdbcTemplate.queryForObject("select * from TEST_1 where id=?", new BeanPropertyRowMapper< Test > (Test.class), new Object[] {Integer.parseInt(id)} );
		return result;
    }

	@GetMapping("/queryAll")
	public List<Map<String, Object>> queryAll() {
		System.out.println("Fetch All");
		List<Map<String, Object>> testobj = jdbcTemplate.query("select * from TEST_1", new ColumnMapRowMapper(),new Object[] {});
		System.out.println(testobj);
		return testobj;
    }
    
    @GetMapping("/add/{name}")
	public String add(@PathVariable("name") String name) {
		System.out.println(name);
		jdbcTemplate.update("INSERT INTO TEST_1 (ID, NAME) VALUES ((SELECT MAX(ID) + 1 FROM TEST_1 ),'" + name + "')");
		return "Added";
	}

	private static final String SCHEDULED_TASKS = "scheduledTasks";

@Autowired
private ScheduledAnnotationBeanPostProcessor postProcessor;

@Autowired
private ScheduledTasks1 scheduledTasks;

@Autowired
private ObjectMapper objectMapper;

@GetMapping(value = "/stopScheduler")
public String stopSchedule(){
    postProcessor.postProcessBeforeDestruction(scheduledTasks, SCHEDULED_TASKS);
    return "OK";
}

@GetMapping(value = "/startScheduler")
public String startSchedule(){
    postProcessor.postProcessAfterInitialization(scheduledTasks, SCHEDULED_TASKS);
    return "OK";
}

@GetMapping(value = "/listScheduler")
public String listSchedules() throws JsonProcessingException{
    Set<ScheduledTask> setTasks = postProcessor.getScheduledTasks();
    if(!setTasks.isEmpty()){
        return objectMapper.writeValueAsString(setTasks);
    }else{
        return "No running tasks !";
    }
 }

}
