package com.example.demo.controller;


import com.example.demo.entity.Car;
import com.example.demo.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarRepository carRepository;


    @GetMapping("/Soocar/admin/cars")
    public String index(Model model) {
        List<Car> carList = carRepository.findAll();
        model.addAttribute("cars", carList);
        return "carList";
    }
    @GetMapping("/Soocar/admin/cars/{licenseplate}")
    public String show(@PathVariable Long licenseplate, Model model){
        Car carentity = carRepository.findById(licenseplate).orElse(null);
        model.addAttribute("car",carentity);
        return "carlicenseplate";
    }

    @GetMapping("/Soocar/admin/cars/accident")
    public String history(Model model) {
        List<Car> carList = carRepository.findCarWithHistory();
        model.addAttribute("cars", carList);
        return "carList";
    }
}
