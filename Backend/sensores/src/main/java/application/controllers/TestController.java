package application.controllers;

import application.core.test.service.TestService;
import application.dto.TestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TestService testService;

    @GetMapping("/")
    public String test() {
        TestDto test = this.testService.findById(1);
        return test.getDescription();
    }

}
