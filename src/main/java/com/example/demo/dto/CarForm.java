package com.example.demo.dto;

import com.example.demo.entity.Car;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarForm {

    @JsonProperty
    private Long licenseplate;
    private String carname;
    private String history;



    public Car toEntity(){
        return new Car(licenseplate, carname, history);

    }

}
