package org.factoriaf5.first_api;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saludar")
public class FirstController {
    @GetMapping

    public String saludar() {
        return "hola";
    }

    @PostMapping
    public String saludar2 (@RequestBody String name) {
        return  "hola por post: "+ name;
    }

    @DeleteMapping("/{id}")
    public String delete (@PathVariable int id){
        return "quieres eliminar el recurso " + id;
    }

    @PutMapping ("/{id}")
    public String update (@PathVariable int id){
        return  "vas a cambiar el nombre por: ";
    }




}
