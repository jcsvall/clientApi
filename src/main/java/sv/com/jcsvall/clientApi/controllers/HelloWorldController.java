package sv.com.jcsvall.clientApi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	@GetMapping("hello")
	public ResponseEntity<String> helloWorld(@RequestParam(value = "name", defaultValue = "World") String name) {
		String retorno = "Hello "+name;
		return new ResponseEntity<String>(retorno, HttpStatus.OK);
	}
}
