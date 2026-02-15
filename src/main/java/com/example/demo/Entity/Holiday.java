package com.example.demo.Entity;
import lombok.Data;


import java.beans.ConstructorProperties;

@Data
public class Holiday {
    private final String day;
    private final String reason;
    private final Type type;


    public enum Type {
        FESTIVAL, FEDERAL
    };

}
