package com.simple.microservices.notification.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.simple.microservices.notification.entity.NotificationTemplate;
@Repository
public interface NotificationTemplateRepository extends JpaRepository<NotificationTemplate, String> {

}