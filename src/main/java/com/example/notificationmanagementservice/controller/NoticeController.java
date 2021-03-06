package com.example.notificationmanagementservice.controller;

import com.example.notificationmanagementservice.dto.NoticeDto;
import com.example.notificationmanagementservice.dto.NoticeResponseDto;
import com.example.notificationmanagementservice.entity.NoticeEntity;
import com.example.notificationmanagementservice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * CREST API to manage notification information.
 *
 * @author FPT Software
 */

@Controller
@RequestMapping("/notices")
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    /**
     * Create new notice
     *
     * @param noticeEntity noticeEntity
     * @return json noticeEntity
     * @throws ParseException If string's byte cannot be obtained
     */
    @PostMapping
    public ResponseEntity<?> createNotice(@ModelAttribute @RequestBody NoticeDto noticeEntity) throws ParseException {
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.createNotice(noticeEntity));
    }

    /**
     * update new notice
     *
     * @param noticeEntity noticeEntity
     * @return delete successful
     * @throws Exception If string's byte cannot be obtained
     */
    @PutMapping
    public ResponseEntity<?> updateNotice(@RequestBody NoticeEntity noticeEntity) throws Exception {
        noticeService.updateNotice(noticeEntity);
        return ResponseEntity.status(HttpStatus.OK).body("update successful");
    }

    /**
     *Delete Notice
     *
     * @param id notice_id
     * @return delete successful

     */
    @DeleteMapping
    public ResponseEntity<?> deleteNotice(@RequestParam Long id) {
        noticeService.deleteNotice(id);
        return ResponseEntity.status(HttpStatus.OK).body("delete successful");
    }

    /**
     *Delete Notice
     *
     * @return list json NoticeEntity

     */
    @GetMapping
    public ResponseEntity<?> getAllNotices() {
        List<NoticeEntity> list = noticeService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getNotice(@PathVariable("id") Long id) throws Exception {
        NoticeResponseDto notice = noticeService.getNotice(id);
        return new ResponseEntity<>(notice, HttpStatus.OK);
    }
}