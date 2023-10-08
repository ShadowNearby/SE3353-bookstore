package com.mainservice.service.serviceimpl;

import com.mainservice.service.TickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;


@Service
//@Scope("session")
public class TickerServiceImpl implements TickerService {

    private final Timer timer;
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private Integer count;
    private TimerTask task;

    public TickerServiceImpl() {
        timer = new Timer();
        count = 0;
        task = null;
    }

    @Override
    public void Begin() {
        count = 0;
        timer.purge();
        if (task != null) {
            task.cancel();
        }
        this.task = new TimerTask() {
            @Override
            public void run() {
                count++;
            }
        };
        timer.schedule(task, 0, 1000);
    }

    @Override
    public Integer End() {
        task.cancel();
        task = null;
        return count;
    }
}
