package com.recorriendoba.util;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {
    public static int leerEntero(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String input = sc.nextLine();
                if (input.trim().isEmpty()) {
                    System.out.println("⚠️ El campo no puede estar vacío.");
                    continue;
                }else{
                    return Integer.parseInt(input.trim());

                }
                
                
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Ingrese un número válido.");
                sc.next();
            }
        }

    }

    public static double leerDouble(Scanner sc, String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double num = Double.parseDouble(sc.nextLine());
                if (num >= 0) return num;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Error: Ingrese un número válido.");
                sc.next(); 
            }
        }

    }

    public static String leerTexto(Scanner sc, String mensaje) {
        System.out.print(mensaje);
        String t = sc.nextLine();
        if (t.trim().isEmpty()) throw new IllegalArgumentException("El campo no puede estar vacío.");
        return t;
    }

    public static LocalDate leerFecha(Scanner sc, String mensaje) {
    while (true) {
        try {
            System.out.print(mensaje + " (formato DD/MM/AAAA): ");
            String entrada = sc.nextLine();
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fecha = LocalDate.parse(entrada, fmt);
            
            if (fecha.isBefore(LocalDate.now())) {
                System.out.println("⚠️ La fecha no puede ser anterior a hoy.");
                continue;
            }
            return fecha;
        } catch (DateTimeParseException e) {
            System.out.println("⚠️ Formato inválido. Use DD/MM/AAAA (ej: 25/05/2026).");
        }
    }
    }
}

