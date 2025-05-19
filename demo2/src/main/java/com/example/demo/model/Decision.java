package com.example.demo.model;

public record Decision(
        String speaker,
        String text,
        String willWork,
        String reason
) {}
