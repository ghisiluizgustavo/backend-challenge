package com.spaceflightnews.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WelcomeController {

    private static final String EMOJI_MEDAL = "\uD83C\uDFC5";

    @GetMapping
    public String welcomeMessage(){
        return "Back-end Challenge 2021 " + EMOJI_MEDAL + " - Space Flight News";
    }
}
