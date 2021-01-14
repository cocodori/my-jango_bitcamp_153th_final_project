package com.dunk.django.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor @AllArgsConstructor @Builder
@Entity
public class FoodType {
    @Id
    private Long id;

    @Column(nullable = false)
    private String type;

    public FoodType(String type) {
        this.type = type;
    }
}