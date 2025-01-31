package com.springboot.messaging.application.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.messaging.application.entity.Message;
import com.springboot.messaging.application.messageservice.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	@PostMapping("/send")
	@ResponseStatus(HttpStatus.CREATED)
	public  Message sendMessage(@RequestBody Map<String,Object>request) {
			 Long senderId=Long.valueOf(request.get("senderId").toString());
			 Long receiverId=Long.valueOf(request.get("receiverId").toString());
			 String content=request.get("content").toString();
		
		return messageService.sendMessage(senderId, receiverId, content);
		
	}
	
	@GetMapping("/received/{receiverId}")
	public List<Message> getMessagesForUser(@PathVariable Long receiverId){
		return messageService.getMessagesForUser(receiverId);
		
		
	}
	
	@PatchMapping("/{messageId}/read")
	public Message markMessageAsRead(@PathVariable Long messageId){
		return messageService.markMessageAsRead(messageId);
	}
	
	
	
	
	

}
