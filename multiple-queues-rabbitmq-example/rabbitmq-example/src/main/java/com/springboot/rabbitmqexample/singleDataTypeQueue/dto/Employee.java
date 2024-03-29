package com.springboot.rabbitmqexample.singleDataTypeQueue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private String name;
    private String mobile;

}
