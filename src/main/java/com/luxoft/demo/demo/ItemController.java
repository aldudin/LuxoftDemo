package com.luxoft.demo.demo;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/item")
public class ItemController {

  @GetMapping("/{id}")
  public Item getItemByID(@PathVariable int id) {
    String description = "";

    switch(id){
      case 1:{
        description = "Apple";
        break;
      }
      case 2: {
        description = "Brussel Sprouts";
        break;
      }
      default:
      {
         throw new ResponseStatusException(
           HttpStatus.NOT_FOUND, "Not a valid Item number");
      }
    }

    return new Item(id,
              description);
  }
}