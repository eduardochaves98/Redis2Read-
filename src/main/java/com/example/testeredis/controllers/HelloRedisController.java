package com.example.testeredis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/redis")

public class HelloRedisController {
    @Autowired
    private RedisTemplate<String, String> template;
    private static final String STRING_KEY_PREFIX = "testeRedis:strings:";

    @GetMapping("/strings/{key}")
    public Map.Entry<String, String> getString(@PathVariable("key") String key) {
        String value = template.opsForValue().get(STRING_KEY_PREFIX + key);

        if (value == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "key not found");
        }

        return new SimpleEntry<String, String>(key, value);
    }

    @PostMapping("/strings")
    @ResponseStatus(HttpStatus.CREATED)
    public Map.Entry<String,String> setString(@RequestBody Map.Entry<String,String> kvp){
        template.opsForValue().set(STRING_KEY_PREFIX + kvp.getKey(), kvp.getValue());
        return kvp;
    }

    @DeleteMapping("strings")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Map.Entry<String,String> deleteString(@RequestBody Map.Entry<String,String> kvp){

        template.delete(STRING_KEY_PREFIX + kvp.getKey());
        return kvp;
    }
}
