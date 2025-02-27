package com.digitalmichael.smds.springmultidatasources.audit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "AuditController")
@RequestMapping(value = "/services/audit")
public class Controller {

    private final AuditManager auditManager;

    private Controller() {
        this.auditManager = null;
    }

    @Autowired
    public Controller(AuditManager auditManager) {
        this.auditManager = auditManager;
    }

}
