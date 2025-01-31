package com.springboot.messaging.application.repository;

import java.util.List;

import org.springframework.data.domain.Page;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.messaging.application.entity.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
	List<Message> findByReceiverId(Long receiverId);
	
	List<Message> findBySenderId(Long senderId);

}
