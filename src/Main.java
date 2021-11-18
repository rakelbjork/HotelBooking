import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        boolean runTime = true;
        int[][] rooms = new int[6][3];
        int[][] roomService = new int[6][3];
        int profit = 0;

        System.out.println("Welcome to the Aurora Elite hotel booking system");
        while (runTime) {
            System.out.println("1: Book a room");
            System.out.println("2: Calculate total profits");
            System.out.println("3: Order room service to a hotel room");
            System.out.println("4: List booked rooms");
            System.out.println("5: Check out a room");
            System.out.println("6: Shutdown the program");
            int choice = input(1);
            switch (choice) {
                case 1 -> Rooms.booking(rooms);
                case 2 -> totalRevenue(rooms, profit);
                case 3 -> roomService(rooms, roomService);
                case 4 -> bookedRooms(rooms);
                case 5 -> {
                    profit = checkout(rooms, profit);
                    System.out.println("Profit earned today: " + profit);
                }
                case 6 -> runTime = false;
                // Skrivs något annat än 1-6 ut skrivs en varning ut
                default -> System.out.println("Wrong input! Please stick to the choices shown.");
            }
        }
    }


    public static int input(int section) {
        int choice;
        while (true) {
            try {
                choice = new Scanner(System.in).nextInt();
                if (section == 0 && choice <= 5 && choice > 0) {
                    return choice;
                }
                if (section == 1 && choice <= 6 && choice > 0) {
                    return choice;
                }
                if (section == 2 && choice <= 4 && choice > 0) {
                    return choice;
                }
                if (section == 3 && choice >= 0) {
                    return choice;
                } else {
                    // Om något annat än de giltiga svaren skrivs ut så får man detta meddelande
                    System.out.println("You entered an invalid number. Please stick to the choices shown.");
                }
                // Hanterar inskriften av bokstäver
            } catch (Exception e) {
                System.out.println("Invalid input! You can only enter numbers!");
            }
        }
    }



    public static void bookedRooms(int[][] rooms) {
        int booked = 0;
        System.out.println("The following rooms are currently booked: ");
        for (int i = 0; rooms.length > i; i++) {
            if (rooms[i][0] >= 1) {
                System.out.println("Room " + (i) + " is booked");
                booked++;
            }
        }
        if (booked == 0) {
            System.out.println("No booked rooms. Please book a room and try again.");
        }
    }


    public static void roomService(int[][] rooms, int[][] roomService) {
        int choice;
        int service;
        int price;
        int bookingAvailable = 0;
        for (int[] room : rooms) {
            if (room[0] >= 1) {
                System.out.println("Which room wants room service?");
                bookingAvailable = 1;
            }
        }
        if (bookingAvailable == 1) {
            while (true) {
                choice = input(1);
                if (rooms[choice][0] == 0) {
                    System.out.println("The room must be booked to order room service. Did you enter the right number?");
                } else {
                    System.out.println("Which type of service do you want to order for the room?");
                    System.out.println("1. Food or drinks from the menu (price varies, non-refundable!)");
                    System.out.println("3. Extra channels on the TV. 50 kr for the entire stay, regardless of days.");
                    System.out.println("4. Extra room cleaning.");
                    try {
                        service = input(0);
                        if (service == 1) {
                            System.out.println("What was the price of the food or drink they ordered?");
                            price = input(3);
                            System.out.println("Room service for room " + (choice) + " ordered to the price of " + price + " SEK.");
                            break;
                        } else if (rooms[choice][2] == 1) {
                            System.out.println("This room already ordered extra channels!");
                            System.out.println("Do you want to unbook them?");
                            System.out.println("1 for yes, 0 for no");
                            try {
                                int unbook = new Scanner(System.in).nextInt();
                                if (unbook == 1) {
                                    System.out.println("Unbooking the channels.");
                                    roomService[choice][2] = 0;
                                    break;
                                }
                                if (unbook == 0) {
                                    System.out.println("Not unbooking the channels.");
                                    break;
                                }
                            } catch (Exception E) {
                                System.out.println("Invalid input! The system is taking that as a no.");
                            }
                        } else if (service == 4 && rooms[choice][4] == 0) {
                            System.out.println("Extra channels for room " + (choice) + " ordered to the price of 50 SEK.");
                            rooms[choice][4] = 1;
                            break;
                        } else if (service == 4 && rooms[choice][4] == 1) {
                            System.out.println("Extra cleaning already ordered for this room. Do you want to unbook it?");
                            System.out.println("Do you want to unbook the cleaning?");
                            System.out.println("1 for yes, 0 for no");
                            try {
                                int unbook = new Scanner(System.in).nextInt();
                                if (unbook == 1) {
                                    System.out.println("Unbooking the extra cleaning.");
                                    roomService[choice][4] = 0;
                                    break;
                                }
                                if (unbook == 0) {
                                    System.out.println("Not unbooking the extra cleaning.");
                                    break;
                                }
                            } catch (Exception E) {
                                System.out.println("Invalid input! The system is taking that as a no.");
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid option!");
                    }
                }
            }
        } else {
            System.out.println("No booked rooms found!");
        }
    }


    public static int checkout(int[][] rooms, int profit) {
        int choice;
        int days;
        int moneyEarned;
        System.out.println("Which room wants to check out?");
        choice = input(0);
        if (rooms[choice][0] >= 1) {
            System.out.println("How many days did the customer stay in room " + rooms[choice][0] + "?");
            days = input(3);
            if (days == 0) {
                System.out.println("The customer did not use the room. Unbooking the room.");
                rooms[choice][0] = 0;
                rooms[choice][1] = 0;
            } else if (rooms[choice][0] == 1) {
                moneyEarned = days * 250;
                profit = checkoutCalculate(rooms, profit, choice, moneyEarned, days);
            } else if (rooms[choice][0] == 2) {
                moneyEarned = days * 150;
                profit = checkoutCalculate(rooms, profit, choice, moneyEarned, days);
            } else if (rooms[choice][0] == 3) {
                moneyEarned = days * 100;
                profit = checkoutCalculate(rooms, profit, choice, moneyEarned, days);
            } else if (rooms[choice][0] == 4) {
                moneyEarned = days * 80;
                profit = checkoutCalculate(rooms, profit, choice, moneyEarned, days);
            } else if (rooms[choice][0] == 5) {
                moneyEarned = days * 40;
                profit = checkoutCalculate(rooms, profit, choice, moneyEarned, days);
            }
        } else {
            System.out.println("Room " + (choice) + " is not booked!");
        }
        return profit;
    }


    public static int checkoutCalculate(int[][] rooms, int profit, int choice, int moneyEarned, int days) {
        profit += moneyEarned + rooms[choice][1];
        rooms[choice][2] += moneyEarned + rooms[choice][1];
        System.out.println("The customer stayed for " + days + ". This means the current revenue for this room was " + moneyEarned + ".");
        System.out.println("The all time profit for this room so far is " + rooms[choice][2] + ".");
        rooms[choice][0] = 0;
        return profit;
    }


    public static void totalRevenue(int[][] rooms, int profit) {
        int notZero = 0;
        for (int i = 1; i < rooms.length; i++) {
            if (rooms[i][1] > 0) {
                System.out.println("Total revenue for room " + rooms[i][0] + " this booking: " + rooms[i][1]);
                System.out.println("Total revenue for room " + rooms[i][0] + " in total: " + rooms[i][2]);
                notZero++;
            }
        }
        if (notZero > 0) {
            System.out.println("\nTotal across all rooms including all bookings:\t" + profit);
        } else {
            System.out.println("No revenue so far. Check out some rooms for a customer first!");
        }
    }
}


