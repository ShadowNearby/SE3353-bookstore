package com.example.demo.dao.daoimpl;

import com.example.demo.dao.ReceiverDao;
import com.example.demo.repository.ReceiverRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReceiverDaoImpl implements ReceiverDao {
    private final ReceiverRepository receiverRepository;

    public ReceiverDaoImpl(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }
}
