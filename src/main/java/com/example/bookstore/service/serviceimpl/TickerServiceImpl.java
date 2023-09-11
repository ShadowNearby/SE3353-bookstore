package com.example.bookstore.service.serviceimpl;

import com.example.bookstore.service.TickerService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.Timer;
import java.util.TimerTask;


@Service
@Scope("session")
public class TickerServiceImpl implements TickerService {

    private final Timer timer;
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
        if (task == null) {
            task = new TimerTask() {
                @Override
                public void run() {
                    count++;
                }
            };
        }
        timer.schedule(task, 0, 1000);
    }

    @Override
    public Integer End() {
        task.cancel();
        task = null;
        return count;
    }
}
