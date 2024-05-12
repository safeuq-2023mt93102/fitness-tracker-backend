package com.bits.group13.fitnesstracker.database;

import com.bits.group13.fitnesstracker.model.Nutrition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Nutrition")
public final class NutritionRecord {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String nutritionType;

  public Nutrition toNutrition() {
    return new Nutrition(id, nutritionType);
  }
}
