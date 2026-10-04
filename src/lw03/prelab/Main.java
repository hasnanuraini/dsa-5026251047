package lw03.prelab;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        problem1();
        problem2();
        problem3();
    }

    public static void problem1() {
        List<String> playlist = new ArrayList<>();

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("playlist.txt")
        );

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ", 3);

            if (parts[0].equals("ADD")) {
                playlist.add(parts[1]);

            } else if (parts[0].equals("INSERT")) {
                int index = Integer.parseInt(parts[1]);
                playlist.add(index, parts[2]);

            } else if (parts[0].equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }

        scanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }
    }

    public static void problem2() {
        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("participants.txt")
        );

        while (scanner.hasNextLine()) {
            String name = scanner.nextLine();

            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        scanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);
    }

    public static void problem3() {
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("inventory.txt")
        );

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    inventory.put(
                        product,
                        inventory.get(product) + quantity
                    );
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)
                        && inventory.get(product) >= quantity) {

                    inventory.put(
                        product,
                        inventory.get(product) - quantity
                    );

                } else {
                    failedSales++;
                }
            }
        }

        scanner.close();

        System.out.println("===== Problem 3 =====");

        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
}