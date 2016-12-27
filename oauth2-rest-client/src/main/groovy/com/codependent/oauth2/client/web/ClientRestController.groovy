package com.codependent.oauth2.client.web

import javax.servlet.http.HttpSession

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.security.oauth2.client.OAuth2RestTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController

@RestController
class ClientRestController {

	@Autowired
	private OAuth2RestTemplate restTemplate
	
	@GetMapping("/users")
	def getUsers(HttpSession session){
		println 'Session id: '+ session.getId()
		HttpHeaders headers = new HttpHeaders();
		ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users', HttpMethod.GET, null, new ParameterizedTypeReference<List<String>>(){}, [])
		response.getBody()
	}
	
	@GetMapping("/users/update")
	def getUsers(@RequestParam String user, HttpSession session){
		println 'Session id: '+ session.getId()
		HttpHeaders headers = new HttpHeaders();
		try{
			ResponseEntity<List<String>> response = restTemplate.exchange('http://localhost:8081/users/'+user, HttpMethod.PUT, null, new ParameterizedTypeReference<List<String>>(){}, [])
			response.getBody()
		}catch(Exception e){
			e.printStackTrace()
			e
		}
	}
	
}
