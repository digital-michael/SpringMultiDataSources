package com.digitalmichael.smds.springmultidatasources.audit;

import com.digitalmichael.smds.springmultidatasources.audit.dto.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@org.springframework.stereotype.Repository(value = "AuditRepository")
interface Repository extends JpaRepository<Entry, Long>, JpaSpecificationExecutor<Entry> {
}