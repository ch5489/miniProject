package com.model2.mvc.web.purchase;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {

	@RequestMapping("/api/demo-web")
	public List<String> Hello() {
		return Arrays.asList("리액트 스프링 ", "연결 성공");
	}

}
