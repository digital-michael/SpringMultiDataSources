package com.digitalmichael.smds.springmultidatasources.log;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile( {"default","all","log"})
@Service( value= "LogManager")
public class LogManager {
}
