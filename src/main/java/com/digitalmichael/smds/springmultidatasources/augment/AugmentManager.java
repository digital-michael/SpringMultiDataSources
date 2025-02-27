package com.digitalmichael.smds.springmultidatasources.augment;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile( {"all","augment"} )
@Service( value= "AugmentManager")
public class AugmentManager {
}
