package com.simple.microservices.notification.controller;
import com.simple.microservices.notification.dto.*; import com.simple.microservices.notification.service.NotificationService; import java.util.List; import org.springframework.http.*; import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }
    @PostMapping
    public ResponseEntity<NotificationDto> create(@RequestBody NotificationDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.create(dto));
    }
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAll(){
        return ResponseEntity.ok(notificationService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getById(@PathVariable String id){
        return ResponseEntity.ok(notificationService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> update(@PathVariable String id, @RequestBody NotificationDto dto){
        return ResponseEntity.ok(notificationService.update(id,dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){ notificationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PostMapping("/templates")
    public ResponseEntity<NotificationTemplateDto> createTemplate(@RequestBody NotificationTemplateDto dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.createTemplate(dto));
    }
    @GetMapping("/templates")
    public ResponseEntity<List<NotificationTemplateDto>> getTemplates(){
        return ResponseEntity.ok(notificationService.getTemplates());
    }
    @PutMapping("/templates/{id}")
    public ResponseEntity<NotificationTemplateDto> updateTemplate(@PathVariable String id, @RequestBody NotificationTemplateDto dto){
        return ResponseEntity.ok(notificationService.updateTemplate(id,dto));
    }
    @DeleteMapping("/templates/{id}")
    public ResponseEntity<Void> deleteTemplate(@PathVariable String id){
        notificationService.deleteTemplate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
