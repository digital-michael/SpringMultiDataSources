package com.digitalmichael.smds.springmultidatasources.augment;

import com.digitalmichael.smds.springmultidatasources.augment.dto.Entry;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Profile( {"all","augment"})
@org.springframework.stereotype.Repository(value = "AugmentRepository")
interface Repository extends JpaRepository<Entry, Long>, JpaSpecificationExecutor<Entry> {
}