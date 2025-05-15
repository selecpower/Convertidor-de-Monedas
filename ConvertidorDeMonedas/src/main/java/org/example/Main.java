package org.example;
import com.google.gson.JsonObject;
import org.example.convertidorservice.ConvertidorService;

import java.io.IOException;
import java.util.Scanner;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConvertidorService service = new ConvertidorService();

        boolean running = true;

        while (running) {
            System.out.println("\n=== Bienvenido al Conversor de Monedas ===");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Dólar (USD) a Peso argentino (ARS)");
            System.out.println("2. Peso argentino (ARS) a Dólar (USD)");
            System.out.println("3. Dólar (USD) a Real brasileño (BRL)");
            System.out.println("4. Real brasileño (BRL) a Dólar (USD)");
            System.out.println("5. Dólar (USD) a Peso chileno (CLP)");
            System.out.println("6. Peso chileno (CLP) a Dólar (USD)");
            System.out.println("7. Dólar (USD) a Peso colombiano (COP)");
            System.out.println("8. Peso colombiano (COP) a Dólar (USD)");
            System.out.println("9. Salir");

            System.out.print("Ingrese su opción: ");
            int option = scanner.nextInt();

            if (option == 9) {
                running = false;
                System.out.println("¡Gracias por usar el conversor!");
                continue;
            }

            System.out.print("Ingrese la cantidad a convertir: ");
            double amount = scanner.nextDouble();

            String from = "";
            String to = "";

            switch (option) {
                case 1 -> { from = "USD"; to = "ARS"; }
                case 2 -> { from = "ARS"; to = "USD"; }
                case 3 -> { from = "USD"; to = "BRL"; }
                case 4 -> { from = "BRL"; to = "USD"; }
                case 5 -> { from = "USD"; to = "CLP"; }
                case 6 -> { from = "CLP"; to = "USD"; }
                case 7 -> { from = "USD"; to = "COP"; }
                case 8 -> { from = "COP"; to = "USD"; }
                default -> {
                    System.out.println("Opción no válida.");
                    continue;
                }
            }

            try {
                JsonObject rates = service.getConversionRates(from);
                if (!rates.has(to)) {
                    System.out.println("Moneda no disponible en la API.");
                    continue;
                }

                double rate = rates.get(to).getAsDouble();
                double result = service.convert(amount, rate);

                System.out.printf("Resultado: %.2f %s = %.2f %s%n", amount, from, result, to);
            } catch (IOException | InterruptedException e) {
                System.out.println("Error al conectar con la API: " + e.getMessage());
            }
        }

        scanner.close();
    }
}