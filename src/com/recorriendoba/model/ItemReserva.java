package com.recorriendoba.model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ItemReserva {
    private Tour tour;
    private java.time.LocalDateTime fechaHora;

    public ItemReserva(Tour tour, LocalDateTime fechaHora) {
        this.tour = tour;
        this.fechaHora = fechaHora;
    }

    public Tour getTour() { return tour; }
    public LocalDateTime getFechaHora() { return fechaHora; }

    @Override
    public String toString() {
       DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("ID Tour: [%d] | %s | %s hs | $%8.2f", 
            tour.getId(),
            tour.getNombre(),
            fechaHora.format(fmt),
            tour.getPrecio());
    }
}
