package ru.gb.itmob.social.registryservice.examples;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;



@Controller
@RequiredArgsConstructor
public class GetUrlExample {


    /*
        НЕ БУДЕТ РАБОТАТЬ ЕСЛИ ЗАПУСТИТЬ НЕПОСРЕДСТВЕННО НА СЕРВИСЕ РЕГИСТРАЦИИ

        тут только для примера
    */

    private final DiscoveryClient discoveryClient;

    @GetMapping("/{name}")
    public List<String> get(@PathVariable String name) {
        return discoveryClient.getInstances(name).stream().map(si -> si.getUri().toString()).toList();
    }

}


