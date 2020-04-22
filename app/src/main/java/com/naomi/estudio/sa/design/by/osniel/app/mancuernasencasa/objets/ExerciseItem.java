package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets;

public class ExerciseItem {
    public final Long id;
    public final String name;

    public ExerciseItem(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
