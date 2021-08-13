package org.harryng.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/heroes")
public class OAuthController {
    static Logger logger = LoggerFactory.getLogger(OAuthController.class);
    private List<Hero> someHeroes = new ArrayList<>();
    @PostConstruct
    protected void init(){
        someHeroes.addAll(List.of(
                new Hero(1, "Ken"),
                new Hero(2, "Yannick"),
                new Hero(3, "Pieter")));
    }

    @GetMapping
    public List<Hero> heroes() {
        logger.info("=== hero list ===");
        return someHeroes;
    }

    @GetMapping("/{id}")
    public Hero hero(@PathVariable("id") String id) {
        logger.info("Find ID:" + id);
        return someHeroes.stream()
                .filter(h -> Integer.toString(h.getId()).equals(id))
                .findFirst()
                .orElse(null);
    }
}

class Hero {
    private final int id;
    private final String name;

    public Hero(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }

    public String getName() { return name; }
}
