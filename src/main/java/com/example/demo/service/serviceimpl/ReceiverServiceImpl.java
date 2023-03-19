package com.example.demo.service.serviceimpl;

import com.example.demo.dao.ReceiverDao;
import com.example.demo.service.ReceiverService;
import org.springframework.stereotype.Service;

@Service
public class ReceiverServiceImpl implements ReceiverService {
    private final ReceiverDao receiverDao;

    public ReceiverServiceImpl(ReceiverDao receiverDao) {
        this.receiverDao = receiverDao;
    }
}
