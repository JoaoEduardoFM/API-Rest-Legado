package br.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import br.com.model.response.Response;

@Service
public class DesligarService {

	@Autowired
	ConfigurableWebApplicationContext context;

	public ResponseEntity<?> shutdown() {
		Response response = new Response();
        TaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.execute(() -> {
        	response.setMessage("Aplicação desligada com sucesso.");
        	response.setStatus(HttpStatus.OK);
            context.close();
            System.exit(0);
        });
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
