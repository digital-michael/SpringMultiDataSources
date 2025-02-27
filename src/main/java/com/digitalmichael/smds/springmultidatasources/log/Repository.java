package com.digitalmichael.smds.springmultidatasources.log;

import com.digitalmichael.smds.springmultidatasources.log.dto.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Profile( {"default","all","log"})
@org.springframework.stereotype.Repository(value = "LogtRepository")
interface Repository extends JpaRepository<Entry, Long>, JpaSpecificationExecutor<Entry> {
}