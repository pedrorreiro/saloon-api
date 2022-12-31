package com.app.saloon.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.saloon.Dtos.SaloonDto;
import com.app.saloon.InputDto.SaloonInputDto;
import com.app.saloon.Service.SaloonService;

@RestController
@RequestMapping("/saloon")
public class SaloonController {

    @Autowired
    SaloonService saloonService;

    @GetMapping
    public List<SaloonDto> listSaloons() {
        return saloonService.getAll();
    }

    @GetMapping("/owner/{ownerId}")
    public SaloonDto findByOwner(@PathVariable int ownerId) {
        return saloonService.findByOwner(ownerId);
    }

    @GetMapping("/{id}")
    public SaloonDto findByIdAndActiveTrue(@PathVariable int id) {
        return saloonService.findByIdAndActiveTrue(id);
    }

    @PostMapping
    public SaloonDto save(@RequestBody SaloonInputDto saloonDto) {
        return saloonService.save(saloonDto);
    }
}
