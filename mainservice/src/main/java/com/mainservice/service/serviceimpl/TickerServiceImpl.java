package com.mainservice.service.serviceimpl;

import com.mainservice.service.TickerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Timer;
import java.util.TimerTask;

//@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)

@Service
@SessionScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
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
        log.info("login {}", this.hashCode());
    }

    @Override
    public Integer End() {
        log.info("logout, login for {} second(s) {}", count, this.hashCode());
        if (task == null) {
            return count;
        }
        task.cancel();
        task = null;
        return count;
    }
}
