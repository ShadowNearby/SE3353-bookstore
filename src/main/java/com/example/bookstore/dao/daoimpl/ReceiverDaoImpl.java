package com.example.bookstore.dao.daoimpl;

import com.example.bookstore.dao.ReceiverDao;
import com.example.bookstore.repository.ReceiverRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ReceiverDaoImpl implements ReceiverDao {
    private final ReceiverRepository receiverRepository;

    public ReceiverDaoImpl(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }
}
