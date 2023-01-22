package com.app.saloon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.saloon.Dtos.FunctionaryDto;
import com.app.saloon.InputDto.FunctionaryInputDto;
import com.app.saloon.Service.FunctionaryService;
import com.app.saloon.Service.ServiceService;

@RestController
@RequestMapping("/functionary")
public class FunctionaryController {

    @Autowired
    FunctionaryService functionaryService;

    @Autowired
    ServiceService serviceService;

    @GetMapping
    public List<FunctionaryDto> listFunctionaries() {
        return functionaryService.getAll();
    }

    @GetMapping("/{id}")
    public FunctionaryDto findById(@PathVariable int id) {
        return functionaryService.findById(id);
    }

    @GetMapping("/user/{userId}")
    public FunctionaryDto findByUser(@PathVariable int userId) {
        return functionaryService.findByUser(userId);
    }

    @GetMapping("/saloon/{saloonId}")
    public List<FunctionaryDto> findBySaloon(@PathVariable int saloonId) {
        return functionaryService.findBySaloon(saloonId);
    }

    @PostMapping()
    public FunctionaryDto save(@RequestBody FunctionaryInputDto functionary) {
        return functionaryService.save(functionary);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        functionaryService.delete(id);
    }

}
