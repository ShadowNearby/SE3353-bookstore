package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.dao.ReceiverDao;
import com.example.bookstore.service.ReceiverService;
import org.springframework.stereotype.Service;

@Service
public class ReceiverServiceImpl implements ReceiverService {
    private final ReceiverDao receiverDao;

    public ReceiverServiceImpl(ReceiverDao receiverDao) {
        this.receiverDao = receiverDao;
    }
}
