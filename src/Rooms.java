public class Rooms {

    public static void booking(int[][] rooms) {
        int choice;
        System.out.println("Which room did the customer want to book?");
        System.out.println("Rum 1: First class, double. 1500 SEK per night.");
        System.out.println("Rum 2. Second class, double. 1000 SEK per night.");
        System.out.println("Rum 3. Third class, single. 800 SEK per night.");
        System.out.println("Rum 4. Fourth class, single. 600 SEK per night.");
        System.out.println("Rum 5. Fifth class, no bed. 400 SEK per night.");

        while (true) {
            try {
                choice = Main.input(0);
                if (choice > 5) {
                    System.out.println("You need to enter a valid room number!");
                } else if (rooms[choice][0] == 0) {
                    rooms[choice][0] = choice;
                    System.out.println("Room " + (choice) + " is now booked!");
                    break;
                } else {
                    System.out.println("The room is already booked!");
                }
            } catch (Exception e) {
                System.out.println("Du får inte skriva in bokstäver eller negativa siffror! Försök igen: ");
            }
        }
    }
}


