package com.springboot.messaging.application.messageservice;

import java.time.LocalDateTime;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.messaging.application.entity.Message;
import com.springboot.messaging.application.repository.MessageRepository;

@Service
public class MessageService {
	
	@Autowired
	private MessageRepository messageRepository;
	
	public Message sendMessage(Long senderId, Long receiverId,String content) {
    if(content==null ||content.isEmpty()) {
    	throw new IllegalArgumentException("Message content cannot be empty");
    }
    
    Message message=new Message();
    message.setSenderId(senderId);
    message.setReceiverId(receiverId);
    message.setContent(content);
    message.setTimestamp(LocalDateTime.now());
    message.setStatus(Message.Status.SENT);
    
    return messageRepository.save(message);
}
	
	public List<Message> getMessagesForUser(Long receiverId){
		return messageRepository.findByReceiverId(receiverId);
	}
	
	public Message markMessageAsRead(Long messageId) {
		Message message=messageRepository.findById(messageId).orElseThrow(() ->new IllegalArgumentException("Message not found"));
		
		message.setStatus(Message.Status.READ);
		return messageRepository.save(message);
	}
}


