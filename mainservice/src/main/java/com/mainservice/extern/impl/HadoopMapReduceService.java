package com.mainservice.extern.impl;

import com.mainservice.extern.MapReduceService;
import org.springframework.stereotype.Service;

@Service
public class HadoopMapReduceService implements MapReduceService {
    @Override
    public String run() {
        return "123";
    }
}
