/*
 Question: Write a Java method isAvailable in the CarRentalShare class that checks if a car rental is available at the current time based on a list of rental times. Each rental time is defined by a start and end LocalDateTime. Implement the method and test it using the provided main method.

input: 
RentalTimes= {
    3/5/2024 13:00 14:00
    3/5/2024 15:00 16:00
    3/5/2024 18:00 20:00
}
 */

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CarRentalShare {
    static boolean isAvailable(List<RentalTime> rentalTimes) {
        if (rentalTimes.size() < 2)
            return true;
        Collections.sort(rentalTimes, Comparator.comparing(RentalTime::getStartLocalDateTime));
        for (int i = 0; i < rentalTimes.size() - 1; i++) {
            RentalTime current = rentalTimes.get(i);
            RentalTime next = rentalTimes.get(i + 1);
            if (current.getEndLocalDateTime().isAfter(next.getStartLocalDateTime()))
                return false;
        }
        return true;
    }

    static boolean isAvailable2(List<RentalTime> rentalTimes) {
        for (int i = 0; i < rentalTimes.size(); i++) {
            RentalTime rentalTime1 = rentalTimes.get(i);
            for (int j = i + 1; j < rentalTimes.size(); j++) {
                RentalTime rentalTime2 = rentalTimes.get(j);
                if (isOverlap(rentalTime1, rentalTime2))
                    return false;
            }
        }
        return true;
    }

    private static boolean isOverlap(RentalTime rt1, RentalTime rt2) {
        LocalDateTime start1 = rt1.getStartLocalDateTime();
        LocalDateTime end1 = rt1.getEndLocalDateTime();
        LocalDateTime start2 = rt2.getStartLocalDateTime();
        LocalDateTime end2 = rt2.getEndLocalDateTime();
        if (end1.isBefore(start2) || end2.isBefore(start1)) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) {
        List<RentalTime> listOfRentalTimes = new ArrayList<>();
        listOfRentalTimes.add(new RentalTime(LocalDateTime.now().minusHours(8), LocalDateTime.now().minusHours(7)));
        listOfRentalTimes.add(new RentalTime(LocalDateTime.now().minusHours(6), LocalDateTime.now().minusHours(5)));
        listOfRentalTimes.add(new RentalTime(LocalDateTime.now().minusHours(3), LocalDateTime.now().minusHours(2)));
        System.out.println(CarRentalShare.isAvailable(listOfRentalTimes)); // Output: true
    }
}

class RentalTime {
    private LocalDateTime startLocalDateTime;
    private LocalDateTime endLocalDateTime;

    public RentalTime(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        this.startLocalDateTime = startLocalDateTime;
        this.endLocalDateTime = endLocalDateTime;
    }

    public LocalDateTime getStartLocalDateTime() {
        return startLocalDateTime;
    }

    public LocalDateTime getEndLocalDateTime() {
        return endLocalDateTime;
    }
}