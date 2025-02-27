package com.digitalmichael.smds.springmultidatasources.audit;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile( {"all","audit"})
@Service( value= "AuditManager")
public class AuditManager {
}
