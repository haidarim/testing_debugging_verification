/*
Origin from http://pages.cs.wisc.edu/~vernon/cs367/tutorials/jdb.tutorial.html
Modified by Jeff C.
*/

class DatesImprove {
    /* Precondition: month is between 1 and 12, inclusive */
    /* Postcondition: returns the number of days in the given month */
    public static int daysInMonth(int month) {
        switch (month) {
            case 1: // Januari
            case 3: // Mars
            case 5: // Maj
            case 7: // Juli
            case 8: // Augusti
            case 10: // Oktober
            case 12: // December
                return 31;
            case 4: // April
            case 6: // Juni
            case 9: // September
            case 11: // November
                return 30;
            case 2: // Februari
                return 28; // Vi antar att februari alltid har 28 dagar för enkelhetens skull
            default:
                throw new IllegalArgumentException("Ogiltigt månadsnummer: " + month);
        }
    }

    public static void main(String[] args) {
        int someMonth, someDay;
        int laterMonth, laterDay;

        someMonth = Integer.parseInt(args[0]) - 1;
        someDay = Integer.parseInt(args[1]);
        laterMonth = Integer.parseInt(args[2]) - 1;
        laterDay = Integer.parseInt(args[3]);

        int aMonth;
        int someDayInYear = 0;
        int laterDayInYear = 0;

        for (aMonth = 0; aMonth < someMonth; aMonth = aMonth + 1) {
            someDayInYear = someDayInYear + daysInMonth(aMonth);
        }
        someDayInYear = someDayInYear + someDay;

        for (aMonth = 0; aMonth < laterMonth; aMonth = aMonth + 1) {
            laterDayInYear = laterDayInYear + daysInMonth(aMonth);
        }
        laterDayInYear = laterDayInYear + laterDay;

        int daysBetween = 0;

        // Bug number 2 - We didnt have any mechanism for when the start was after the
        // end
        if (laterDayInYear < someDayInYear) {
            daysBetween = 365 + laterDayInYear - someDayInYear;
        } else {
            daysBetween = laterDayInYear - someDayInYear;
        }

        System.out.println("The difference in days between " + (someMonth + 1) + "/" + someDay + " and "
                + (laterMonth + 1) + "/" + laterDay + " is: ");
        System.out.println(daysBetween);
    }
}
