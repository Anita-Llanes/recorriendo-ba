package com.recorriendoba.service;
import com.recorriendoba.model.*;
import java.util.*;

public class TourService {
    private List<Tour> catalogo = new ArrayList<>();
    private static int contadorId = 1;

    public void guardar(Tour t) {
        t.setId(contadorId++);
        catalogo.add(t);
    }

    public List<Tour> listarTodo() { return catalogo; }

    public Tour buscarTour(String criterio) {
        return catalogo.stream()
                .filter(t -> String.valueOf(t.getId()).equals(criterio.trim()) || t.getNombre().toLowerCase().contains(criterio.toLowerCase().trim()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No se encontró: " + criterio));
    }

    public void modificarTour(int id, String nuevoNom, String nuevaDes, double nuevoPre, int nuevoSto) {
        Tour t = buscarTour(String.valueOf(id));
        if (nuevoNom != null && !nuevoNom.trim().isEmpty()) {
            t.setNombre(nuevoNom);
        }
        if (nuevaDes != null && !nuevaDes.trim().isEmpty()) {
        t.setDescripcion(nuevaDes);
        }
        if (nuevoPre > 0) {
        t.setPrecio(nuevoPre);
        }
        if (nuevoSto >= 0) {
        t.setStock(nuevoSto);
        }
    
        System.out.println("✅ Tour ID " + id + " actualizado con éxito.");
    }

    public void eliminarTour(int id) {
        boolean eliminado = catalogo.removeIf(t -> t.getId() == id);
    
        if (eliminado) {
        System.out.println("✅ El tour con ID " + id + " fue eliminado del sistema.");
        } else {
        throw new RuntimeException("No se pudo eliminar: No existe un tour con el ID " + id);   
        }
}
    

    public void mostrarReporteAdmin(){
        System.out.println("\n--- 📊 Reporte de Ventas y stock ---");
        System.out.printf("%-3s | %-20s | %-10s | %-10s | %-10s\n", "ID", "Nombre", "Vendidos", "Stock", "Precio unitario");
        System.out.println("------------------------------------------------------------------");
        catalogo.forEach(t -> {
            System.out.printf("%03d | %-20s | %-10d | %-10d | $%8.2f\n", 
                t.getId(), t.getNombre(), t.getVendidos(), t.getStock(), t.getPrecio());
        });
    }
}
    