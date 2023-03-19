package com.example.demo.controller;

import com.example.demo.service.ReceiverService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiverController {
    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }
}
