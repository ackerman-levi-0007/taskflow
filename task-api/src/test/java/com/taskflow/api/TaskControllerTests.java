package com.taskflow.api;

import com.taskflow.api.controller.TaskController;
import com.taskflow.api.dto.TaskResponse;
import com.taskflow.api.enums.TaskPriority;
import com.taskflow.api.enums.TaskStatus;
import com.taskflow.api.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TaskController.class)
class TaskControllerTests {

	@Autowired
	MockMvc mvc;

	@MockBean
	TaskService taskService;

	@Test
	void post_returns201_andLocationHeader() throws Exception{
		var response = TaskResponse.builder()
				.id(1L)
				.title("t")
				.description("d")
				.status(TaskStatus.PENDING)
				.priority(TaskPriority.MEDIUM)
				.createdAt(Instant.now())
				.updatedAt(Instant.now())
				.build();

		when(taskService.create(any())).thenReturn(response);

		mvc.perform(post("/api/v1/tasks")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"title\":\"t\",\"description\":\"d\",\"priority\":\"MEDIUM\"}")
		)
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", containsString("/api/v1/tasks/1")));
	}

}
