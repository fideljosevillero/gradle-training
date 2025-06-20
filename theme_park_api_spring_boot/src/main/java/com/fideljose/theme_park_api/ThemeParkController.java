package com.fideljose.theme_park_api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Iterator;

@RestController
public class ThemeParkController {
    @GetMapping(path = "/rides")
    public Iterator<ThemeParkRide> getRides(){
        System.out.println("**********************************************");
        return Arrays.asList(
                new ThemeParkRide("logflume", "description to logflume"),
                new ThemeParkRide("rollercoaster", "description to rollercoaster"),
                new ThemeParkRide("teacups", "description to teacups"),
                new ThemeParkRide("***", "*************************************")
        ).iterator();
    }
}
