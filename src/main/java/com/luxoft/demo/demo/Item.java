package com.luxoft.demo.demo;

public class Item {

    private final long id;
    private final String description;
  
    public Item(long id, String description) {
      this.id = id;
      this.description = description;
    }
  
    public long getId() {
      return id;
    }
  
    public String getDescription() {
      return description;
    }
  }
