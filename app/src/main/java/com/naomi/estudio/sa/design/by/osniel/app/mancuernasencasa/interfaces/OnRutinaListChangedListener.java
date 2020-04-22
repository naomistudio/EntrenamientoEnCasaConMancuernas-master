package com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.interfaces;

import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Entrenamiento;
import com.naomi.estudio.sa.design.by.osniel.app.mancuernasencasa.objets.Rutina;

import java.util.List;

public interface OnRutinaListChangedListener {
    void onRutinaListChanged(List<Rutina> rutinaList);
}
