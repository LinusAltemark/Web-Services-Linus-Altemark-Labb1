package spi;

import java.util.Scanner;
import java.util.ServiceLoader;

public class CurrencyApp {
    public static void main(String[] args) {
        ServiceLoader<CurrencyConverter> loader = ServiceLoader.load(CurrencyConverter.class);
        CurrencyConverter[] converters = loader.stream()
                .map(ServiceLoader.Provider::get)
                .toArray(CurrencyConverter[]::new);

        if (converters.length == 0) {
            System.out.println("Inga valutakonverterare hittades.");
            return;
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Valutakonverterare ===");
            for (int i = 0; i < converters.length; i++) {
                System.out.println((i + 1) + ". " + converters[i].getSourceCurrency() + " → SEK");
            }
            System.out.println("0. Avsluta");

            System.out.print("Välj ett alternativ: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Ogiltig inmatning.");
                continue;
            }

            if (choice == 0) {
                System.out.println("Avslutar programmet.");
                break;
            }

            if (choice < 1 || choice > converters.length) {
                System.out.println("Ogiltigt val, försök igen.");
                continue;
            }

            CurrencyConverter selected = converters[choice - 1];
            System.out.print("Ange belopp i " + selected.getSourceCurrency() + ": ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                double sek = selected.convertToSek(amount);
                System.out.printf("%.2f %s = %.2f SEK%n", amount, selected.getSourceCurrency(), sek);
            } catch (NumberFormatException e) {
                System.out.println("Ogiltigt belopp.");
            }
        }
    }
}
