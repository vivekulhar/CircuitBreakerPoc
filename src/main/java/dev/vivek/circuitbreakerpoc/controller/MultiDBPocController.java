package dev.vivek.circuitbreakerpoc.controller;

import dev.vivek.circuitbreakerpoc.service.MultiDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MultiDBPocController {
    @Autowired
    private MultiDBService multiDBService;

    @GetMapping("getData/{flag}")
    public Iterable<?> getData(@PathVariable String flag) {
        System.out.println("===========================");
        return multiDBService.getEmpData(flag);
    }
}
