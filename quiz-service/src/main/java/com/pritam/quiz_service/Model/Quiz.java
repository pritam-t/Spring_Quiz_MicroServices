package com.pritam.quiz_service.Model;

import jakarta.persistence.*;
import lombok.Data;
import org.bouncycastle.util.Integers;

import java.util.List;

@Entity
@Data
public class Quiz
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    @ElementCollection
    private List<Integer> questions;
}
