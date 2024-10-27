package com.example.demo;

import com.example.demo.service.ActivitiService;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;

@SpringBootTest
class Demo1ApplicationTests {

	@Autowired
	private ActivitiService activitiService;

	@Autowired
	private ProcessEngine processEngine;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;

	@Autowired
	private RepositoryService repositoryService;

	@Test
	void contextLoads() {
		// 部署流程-流程图在resources/processes下
		repositoryService.createDeployment()
				.addClasspathResource("processes/Test1.bpmn20.xml")
				.deploy();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Test1");
		System.out.println("流程实例ID：" + processInstance.getId());
		System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());

		// 查询任务
		List<Task> taskList = taskService.createTaskQuery().taskAssignee("zhangsan").list();
		for (Task task : taskList) {
			System.out.println("任务ID：" + task.getId());
			System.out.println("任务名称：" + task.getName());
			System.out.println("任务创建时间：" + task.getCreateTime());
			System.out.println("任务委派人：" + task.getAssignee());
			System.out.println("流程实例ID：" + task.getProcessInstanceId());
			System.out.println("执行对象ID：" + task.getExecutionId());
			System.out.println("流程定义ID：" + task.getProcessDefinitionId());
		}


	}




}
