package com.digitalmichael.smds.springmultidatasources.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Profile( {"default","all","log"})
@RestController(value = "LogController")
@RequestMapping(value = "/services/log")
public class Controller {

    private final LogManager logManager;

    private Controller() {
        this.logManager = null;
    }

    @Autowired
    public Controller(LogManager logManager) {
        this.logManager = logManager;
    }

}
