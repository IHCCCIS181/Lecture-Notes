package com.example.accessingdatamysql;

import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;

@Entity // This tells Hibernate to make a table out of this class
@Data
public class User {
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private @Nullable Integer id;

  private String name;

  private String email;
}