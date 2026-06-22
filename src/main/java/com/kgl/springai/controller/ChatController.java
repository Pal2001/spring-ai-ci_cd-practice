package com.kgl.springai.controller;

import java.util.HashMap;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {
	
	private ChatClient chatClient;
	
	public ChatController(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}
	
	@PostMapping("/chat")
	public ResponseEntity<String> chat(@RequestParam(value="q", required = true) String query){
		
		
		
		var resultResponse = chatClient.prompt(query).call().content(); 
		
		return ResponseEntity.ok(resultResponse);
		
	}

	public String get_market_data() {
	    return "market_sentiment: Bearish" ;
	    }
	 
	
	@PostMapping("/dchat")
	public ResponseEntity<String> chat(){
		
		String question =" Should I invest 10,00,000 in technology stocks?";
		 
		String tool_output = get_market_data();
		 
		String prompt = question + tool_output;
		
		
		
		var resultResponse = chatClient.prompt(prompt).call().content(); 
		
		return ResponseEntity.ok(resultResponse);
		
	}
	 
	
	 
}
