package com.recorriendoba;

import com.recorriendoba.model.*;
import com.recorriendoba.service.*;
import com.recorriendoba.util.Validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        TourService tourService = new TourService();
        CarritoService carrito = new CarritoService();
        CategoriaService catService = new CategoriaService();

        catService.guardar("Gastronomía");
        catService.guardar("Histórico");
        catService.guardar("Cultural");
        catService.guardar("Aventura");
        
        CategoriaTour catGastro = catService.buscarPorId(1);
        CategoriaTour catHistorico = catService.buscarPorId(2);
        CategoriaTour catCultural = catService.buscarPorId(3);
        //CategoriaTour catAventura = catService.buscarPorId(4);
        //CategoriaTour Validator = catService.buscarPorId(5);

        tourService.guardar(new TourGenerico("Parrilla Palermo", "Disfrutar de la mejor carne en un ambiente acogedor.",125, 20, catGastro));
        tourService.guardar(new TourGenerico("Casco Histórico", "Recorrido caminando del casco antiguo de la ciudad.", 50.0, 20, catHistorico));
        tourService.guardar(new TourGenerico("Museo de Arte Moderno - MAM", "Visita guiada por el museo más importante de la ciudad", 80.0, 20, catCultural));


        int op = 0;
        do {
            System.out.println("\n====================================");
            System.out.println("   🏙️  RECORRIENDO BUENOS AIRES ");
            System.out.println("====================================");
            System.out.println("1. Explorar Catálogo");
            System.out.println("2. Ver Carrito");
            System.out.println("3. Eliminar del Carrito");
            System.out.println("4. Confirmar Reserva y Pagar");
            System.out.println("------------------------------------");
            System.out.println("9. Administración");
            System.out.println("0. Salir");
            System.out.println("====================================");
            
            try {
                op = Validator.leerEntero(sc, "Seleccione una opción: ");
                
                switch (op) {
                    case 1 -> {
                        System.out.println("\n--- CATÁLOGO DE TOURS ---");
                        tourService.listarTodo().forEach(System.out::println);
                        String criterio = Validator.leerTexto(sc, "Ingrese ID o Nombre del tour a reservar: ");
                        Tour t = tourService.buscarTour(criterio);

                        LocalDate fecha = Validator.leerFecha(sc, "Ingrese la fecha del tour");
                        
                        int hora = Validator.leerEntero(sc, "Elija hora de inicio (7, 10, 13, 16, 19, 22): ");
                        LocalDateTime fechaHoraCompleta = fecha.atTime(hora, 0);

                        carrito.agregar(new ItemReserva(t, fechaHoraCompleta));
                        System.out.println("✅ '" + t.getNombre() + "' el día " + fecha + " a las " + hora + ":00 hs agregado exitosamente.");
                    }
                    case 2 -> {
                        System.out.println("\n--- 🛒 TU CARRITO ---");
                        if (carrito.getItems().isEmpty()){
                            System.out.println("Tu carrito está vacío.");
                        
                        } else{
                            carrito.getItems().forEach(System.out::println);
                            System.out.println("------------------------------------");
                            System.out.println("TOTAL A PAGAR: $" + carrito.calcularTotal());
                        }
                    }
                    case 3 -> {
                        System.out.println("\n--- ELIMINAR DEL CARRITO ---");
                        if (carrito.getItems().isEmpty()) {
                            System.out.println("No hay nada para eliminar.");
                        } else {
                                carrito.getItems().forEach(System.out::println);

                            int id = Validator.leerEntero(sc, "Ingrese el ID del tour que desea eliminar del carrito: ");
                            carrito.eliminarItemPorId(id);
                            System.out.println("✅ Tour eliminado del carrito.");
                        }
                     }
                    case 4 -> {
                        if (carrito.getItems().isEmpty()) throw new Exception("Carrito vacío.");
                        System.out.println("¡Reserva confirmada! 💳 Procesando pago y generando voucher...");
                        
                        carrito.getItems().forEach(item -> {
                            Tour t = item.getTour();
                            t.setStock(t.getStock() - 1);
                            t.setVendidos(t.getVendidos() + 1);
                        });
                        carrito.vaciar();
                        System.out.println("✅ ¡Gracias por tu compra! Tu voucher ha sido enviado a tu correo.");
                    }
                    case 9 -> {
                            int subOp = 0;
                        do {
                            System.out.println("\n--- 🛠️ ADMINISTRACIÓN ---");
                            System.out.println("1. Consultar Catálogo  | 2. Nuevo Tour");
                            System.out.println("3. Editar/Modificar    | 4. Eliminar Tour");
                            System.out.println("5. Gestionar Categorías| 6. Reporte de Ventas");
                            System.out.println("0. Volver al Menú Principal");
                            
                            subOp = Validator.leerEntero(sc, "Seleccione una opción: ");
                            
                            switch (subOp) {
                                case 1 -> { // CONSULTAR CATÁLOGO
                                    System.out.println("\n--- ESTADO ACTUAL DEL CATÁLOGO ---");
                                    if (tourService.listarTodo().isEmpty()) {
                                        System.out.println("No hay tours registrados.");
                                    } else {
                                        tourService.listarTodo().forEach(System.out::println);
                                    }
                                }
                                
                                case 2 -> { // NUEVO
                                    System.out.println("\nSeleccione una Categoría para el nuevo tour:");
                                    catService.listarTodas().forEach(System.out::println);
                                    int idCat = Validator.leerEntero(sc, "ID de Categoría: ");
                                    CategoriaTour cat = catService.buscarPorId(idCat);
                                    
                                    String n = Validator.leerTexto(sc, "Nombre: ");
                                    String d = Validator.leerTexto(sc, "Descripción: ");
                                    double p = Validator.leerDouble(sc, "Precio: ");
                                    int s = Validator.leerEntero(sc, "Stock Inicial: ");
                                    
                                    tourService.guardar(new TourGenerico(n, d, p, s, cat));
                                    System.out.println("✅ Tour creado exitosamente.");
                                }
                                
                                case 3 -> { // EDITAR
                                    tourService.listarTodo().forEach(System.out::println);
                                    int id = Validator.leerEntero(sc, "ID del tour a modificar: ");
                                    
                                    System.out.println("(Deje vacío para mantener el valor actual)");
                                    String n = Validator.leerTexto(sc, "Nuevo Nombre: ");
                                    String d = Validator.leerTexto(sc, "Nueva Descripción: ");
                                    double p = Validator.leerDouble(sc, "Nuevo Precio (0 para no cambiar): ");
                                    int s = Validator.leerEntero(sc, "Nuevo Stock (-1 para no cambiar): ");
                                    
                                    tourService.modificarTour(id, n, d, p, s);
                                    System.out.println("✅ Tour actualizado.");
                                }
                                
                                case 4 -> { // ELIMINAR
                                    tourService.listarTodo().forEach(System.out::println);
                                    int id = Validator.leerEntero(sc, "ID del tour a ELIMINAR: ");
                                    tourService.eliminarTour(id);
                                    System.out.println("🗑️ Tour eliminado.");
                                }
                                
                                case 5 -> { // CATEGORÍAS
                                    System.out.println("1. Ver Categorías | 2. Nueva Categoría");
                                    int catOp = Validator.leerEntero(sc, "Seleccione: ");
                                    if (catOp == 1) {
                                        catService.listarTodas().forEach(System.out::println);
                                    } else {
                                        String nCat = Validator.leerTexto(sc, "Nombre: ");
                                        catService.guardar(nCat);
                                        System.out.println("✅ Categoría agregada.");
                                    }
                                }
                                
                                case 6 -> tourService.mostrarReporteAdmin();
                                
                                case 0 -> System.out.println("Saliendo del panel de administración...");
                                
                                default -> System.out.println("⚠️ Opción no válida.");
                            }
                        } while (subOp != 0);
                    }
                }
            } catch (Exception e) {
                System.out.println("\n⚠️ ERROR: " + e.getMessage());
            }
        } while (op != 0);
        System.out.println("¡Gracias por confiar en Recorriendo BA!");
        sc.close();
        
    }
}