package lw02.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        LinkedList<String[]> transactions = new LinkedList<>();
        LinkedList<String[]> foods = new LinkedList<>();
        LinkedList<String[]> drinks = new LinkedList<>();
        LinkedList<String[]> successful = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("orders.txt")
        );

        while (scanner.hasNext()) {
            String[] transaction = new String[4];

            transaction[0] = scanner.next();
            transaction[1] = scanner.next();
            transaction[2] = scanner.next();
            transaction[3] = scanner.next();

            transactions.add(transaction);
        }

        scanner.close();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        queue.addAll(transactions);

        while (!queue.isEmpty()) {

            String[] transaction = queue.poll();

            String name = transaction[0];
            String food = transaction[1];
            String drink = transaction[2];
            String table = transaction[3];

            String[] foodData = null;
            String[] drinkData = null;

            // Cari makanan
            if (!food.equals("-")) {
                for (String[] data : foods) {
                    if (data[0].equals(food)) {
                        foodData = data;
                        break;
                    }
                }
            }

            // Cari minuman
            if (!drink.equals("-")) {
                for (String[] data : drinks) {
                    if (data[0].equals(drink)) {
                        drinkData = data;
                        break;
                    }
                }
            }

            boolean success = true;

            // Cek makanan
            if (!food.equals("-")) {
                if (foodData == null || Integer.parseInt(foodData[1]) <= 0) {
                    success = false;
                }
            }

            // Cek minuman
            if (!drink.equals("-")) {
                if (drinkData == null || Integer.parseInt(drinkData[1]) <= 0) {
                    success = false;
                }
            }

            // Jika berhasil
            if (success) {

                if (!food.equals("-")) {
                    int stock = Integer.parseInt(foodData[1]);
                    foodData[1] = String.valueOf(stock - 1);
                }

                if (!drink.equals("-")) {
                    int stock = Integer.parseInt(drinkData[1]);
                    drinkData[1] = String.valueOf(stock - 1);
                }

                successful.add(transaction);

            } else {
                failed.push(transaction);
            }
        }

        // Output successful orders
        System.out.println("=== Successfully Processed Orders ===");

        for (String[] transaction : successful) {
            System.out.println(
                transaction[0] + " " +
                transaction[1] + " " +
                transaction[2] + " " +
                transaction[3]
            );
        }

        // Output sisa makanan
        System.out.println("\n=== Remaining Food Stock ===");

        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        // Output sisa minuman
        System.out.println("\n=== Remaining Drink Stock ===");

        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        // Output failed orders
        System.out.println("\n=== Failed Orders ===");

        while (!failed.isEmpty()) {
            String[] transaction = failed.pop();

            System.out.println(
                transaction[0] + " " +
                transaction[1] + " " +
                transaction[2] + " " +
                transaction[3]
            );
        }
    }
}