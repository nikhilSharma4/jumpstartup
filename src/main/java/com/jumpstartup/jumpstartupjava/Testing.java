package com.jumpstartup.jumpstartupjava;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Testing {
    @GetMapping
    public String test() {
        return "Hello from the other side";
    }
}

