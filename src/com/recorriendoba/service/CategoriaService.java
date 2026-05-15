package com.recorriendoba.service;

import com.recorriendoba.model.CategoriaTour;
import java.util.*;

public class CategoriaService {
    private List<CategoriaTour> categorias = new ArrayList<>();
    private int contadorId = 1;

    public void guardar(String nombre) {
        categorias.add(new CategoriaTour(contadorId++, nombre));
    }

    public List<CategoriaTour> listarTodas() { return categorias; }

    public CategoriaTour buscarPorId(int id) {
    for (CategoriaTour c : categorias) {
        if (c.getId() == id) return c;
    }
    throw new RuntimeException("Categoría ID " + id + " no existe.");
    }

    public void eliminarCategoria(int id) {
        // Cuidado: En un sistema real, no podrías borrar una categoría si tiene tours asociados.
        categorias.removeIf(c -> c.getId() == id);
    }
}