package lw03.prelab;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new ArrayList<>();

        Scanner sc1 = new Scanner(
            Main.class.getResourceAsStream("playlist.txt")
        );

        while (sc1.hasNextLine()) {
            String line = sc1.nextLine();
            String[] parts = line.split(" ", 3);

            String operation = parts[0];
            String song = parts[1];

            if (operation.equals("ADD")) {
                playlist.add(song);

            } else if (operation.equals("INSERT")) {
                int index = Integer.parseInt(parts[1]);
                String songName = parts[2];
                playlist.add(index, songName);

            } else if (parts[0].equals("REMOVE")) {
                playlist.remove(parts[1]);
            }
        }

        sc1.close();

        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }


        System.out.println("===== Problem 2 =====");

        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner sc2 = new Scanner(
            Main.class.getResourceAsStream("participants.txt")
        );

        while (sc2.hasNextLine()) {
            String name = sc2.nextLine();

            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        sc2.close();

        System.out.println("Unique participants: " + participants.size());

        int number = 1;

        for (String name : participants) {
            System.out.println(number + ". " + name);
            number++;
        }

        System.out.println("Duplicate registrations: " + duplicateRegistrations);


        System.out.println("===== Problem 3 =====");

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));

        while (sc3.hasNextLine()) {
            String line = sc3.nextLine();
            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)
                        && inventory.get(product) >= quantity) {

                    inventory.put(product,inventory.get(product) - quantity
                    );

                } else {
                    failedSales++;
                }
            }
        }

        sc3.close();

        for (String product : inventory.keySet()) {
            System.out.println(product + ": " + inventory.get(product));
        }

        System.out.println("Failed sales: " + failedSales);
    }
}