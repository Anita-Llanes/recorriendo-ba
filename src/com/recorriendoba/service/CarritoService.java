package com.recorriendoba.service;
import com.recorriendoba.model.ItemReserva;
import java.time.Duration;
import java.util.*;

public class CarritoService {
    private List<ItemReserva> items = new ArrayList<>();

    public void agregar(ItemReserva nuevo) throws Exception {
        if (nuevo.getTour().getStock() <= 0) {
            throw new Exception("Lo sentimos, no quedan cupos disponibles para "+ nuevo.getTour().getNombre());
        }
        
        for (ItemReserva i : items) {
            long diff = Math.abs(Duration.between(i.getFechaHora(), nuevo.getFechaHora()).toHours());
            if (diff < 3) 
                throw new Exception("Conflicto de horario con '" + i.getTour().getNombre() + "'. Debe dejar mínimo 3 horas entre el inicio de cada tour.");
        }
        
        items.add(nuevo);
    }

    public List<ItemReserva> getItems() {
        return items;
    }

    public void eliminarItemPorId(int idTourBuscado) {
        boolean eliminado = items.removeIf(i -> i.getTour().getId() == idTourBuscado);
    
        if (!eliminado) {
        throw new RuntimeException("No se encontró un tour con ID " + idTourBuscado + " en tu carrito.");
        }        
    }

    public double calcularTotal() {
        return items.stream().mapToDouble(i -> i.getTour().getPrecio()).sum();
    }
    
    public void vaciar() { 
        items.clear(); }
}