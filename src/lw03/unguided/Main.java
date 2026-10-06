package lw03.unguided;

import java.util.*;

public class Main {

   public static void main(String[] args) {
     
        Set<String> registeredStudents = new LinkedHashSet<>();

        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));

        while (sc1.hasNextLine()) {
            String id = sc1.nextLine();
            registeredStudents.add(id);
        }

        sc1.close();

        System.out.println("===== Event Check-In Results =====");
        Set<String> checkedInStudents = new LinkedHashSet<>();
        int rejectedAttempts = 0;

        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));

        while (sc2.hasNextLine()) {
            String id = sc2.nextLine();

            if (!registeredStudents.contains(id)) {
                System.out.println(id + ": Rejected (not registered)");
                rejectedAttempts++;

            } else if (checkedInStudents.contains(id)) {
                System.out.println(id + ": Rejected (already checked in)");
                rejectedAttempts++;

            } else {
                checkedInStudents.add(id);

                System.out.println(id + ": Checked in");
            }
        }

        sc2.close();

        int absentStudents = registeredStudents.size() - checkedInStudents.size();

        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println( "Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + absentStudents);
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}

