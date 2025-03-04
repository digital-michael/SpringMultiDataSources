package com.digitalmichael.smds.springmultidatasources.audit;

import com.digitalmichael.smds.springmultidatasources.audit.dto.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Profile( {"all","audit"})
@org.springframework.stereotype.Repository(value = "AuditRepository")
interface Repository extends JpaRepository<Entry, Long>, JpaSpecificationExecutor<Entry> {
}