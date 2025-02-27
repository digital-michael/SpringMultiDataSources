package com.digitalmichael.smds.springmultidatasources.augment;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "AugmentController")
@RequestMapping(value = "/services/augment")
public class Controller {

    private final AugmentManager augmentManager;

    private Controller() {
        this.augmentManager = null;
    }

    @Autowired
    public Controller(AugmentManager augmentManager) {
        this.augmentManager = augmentManager;
    }

}
