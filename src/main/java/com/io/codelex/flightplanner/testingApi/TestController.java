package com.io.codelex.flightplanner.testingApi;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testing-api")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService){
        this.testService = testService;
    }

    @PostMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clear(){
        testService.clear();
    }
}
