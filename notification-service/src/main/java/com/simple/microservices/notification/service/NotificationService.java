package com.simple.microservices.notification.service;

import com.simple.microservices.notification.dto.*;
import com.simple.microservices.notification.entity.*;
import com.simple.microservices.notification.exception.ResourceNotFoundException;
import com.simple.microservices.notification.repository.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

	private final NotificationRepository notificationRepository;
	private final NotificationTemplateRepository templateRepository;

	public NotificationService(NotificationRepository notificationRepository,
			NotificationTemplateRepository templateRepository) {
		this.notificationRepository = notificationRepository;
		this.templateRepository = templateRepository;
	}

	public NotificationDto create(NotificationDto dto) {
		Notification n = toEntity(dto);
		n.setId(null);
		return toDto(notificationRepository.save(n));
	}

	public List<NotificationDto> getAll() {
		return notificationRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public NotificationDto getById(String id) {
		return toDto(notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id)));
	}

	public NotificationDto update(String id, NotificationDto dto) {
		Notification n = notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id));
		n.setType(dto.getType());
		n.setRecipient(dto.getRecipient());
		n.setSubject(dto.getSubject());
		n.setMessage(dto.getMessage());
		n.setStatus(dto.getStatus());
		n.setMetadata(dto.getMetadata());
		return toDto(notificationRepository.save(n));
	}

	public void delete(String id) {
		notificationRepository.delete(notificationRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Notification not found with id: " + id)));
	}

	public NotificationTemplateDto createTemplate(NotificationTemplateDto dto) {
		NotificationTemplate t = new NotificationTemplate();
		t.setCode(dto.getCode());
		t.setChannel(dto.getChannel());
		t.setSubject(dto.getSubject());
		t.setBody(dto.getBody());
		return toDto(templateRepository.save(t));
	}

	public List<NotificationTemplateDto> getTemplates() {
		return templateRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
	}

	public NotificationTemplateDto updateTemplate(String id, NotificationTemplateDto dto) {
		NotificationTemplate t = templateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Template not found with id: " + id));
		t.setCode(dto.getCode());
		t.setChannel(dto.getChannel());
		t.setSubject(dto.getSubject());
		t.setBody(dto.getBody());
		return toDto(templateRepository.save(t));
	}

	public void deleteTemplate(String id) {
		templateRepository.delete(templateRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Template not found with id: " + id)));
	}

	@KafkaListener(topics = "${app.kafka.order-created-topic}", groupId = "notification-group")
	public void consumeOrderCreated(OrderCreatedEvent event) {
		Notification notification = new Notification();
		notification.setUserId(event.getUserId());
		notification.setOrderId(event.getOrderId());
		notification.setType("ORDER_CREATED");
		notification.setRecipient(event.getEmail());
		notification.setSubject("Order Confirmation - #" + event.getOrderId());
		notification.setMessage("Hi " + event.getUsername()
				+ ", your order has been created successfully. Total amount: " + event.getTotalAmount());
		notification.setStatus("CREATED");
		Map<String, Object> metadata = new HashMap<>();
		metadata.put("orderStatus", event.getStatus());
		metadata.put("channel", "EMAIL");
		notification.setMetadata(metadata);
		notificationRepository.save(notification);
	}

	private Notification toEntity(NotificationDto dto) {
		Notification n = new Notification();
		n.setId(dto.getId());
		n.setUserId(dto.getUserId());
		n.setOrderId(dto.getOrderId());
		n.setType(dto.getType());
		n.setRecipient(dto.getRecipient());
		n.setSubject(dto.getSubject());
		n.setMessage(dto.getMessage());
		n.setStatus(dto.getStatus());
		n.setMetadata(dto.getMetadata());
		return n;
	}

	private NotificationDto toDto(Notification n) {
		NotificationDto dto = new NotificationDto();
		dto.setId(n.getId());
		dto.setUserId(n.getUserId());
		dto.setOrderId(n.getOrderId());
		dto.setType(n.getType());
		dto.setRecipient(n.getRecipient());
		dto.setSubject(n.getSubject());
		dto.setMessage(n.getMessage());
		dto.setStatus(n.getStatus());
		dto.setMetadata(n.getMetadata());
		dto.setCreatedAt(n.getCreatedAt());
		return dto;
	}

	private NotificationTemplateDto toDto(NotificationTemplate t) {
		NotificationTemplateDto dto = new NotificationTemplateDto();
		dto.setId(t.getId());
		dto.setCode(t.getCode());
		dto.setChannel(t.getChannel());
		dto.setSubject(t.getSubject());
		dto.setBody(t.getBody());
		return dto;
	}
}
