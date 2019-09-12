import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Main {
    // Travel through a mansion.
    // Ask user what direction they wish to travel in.
    // Move user to requested room and tell them
    // what is in it and what direction the exits are.
    // Each room has its own method.
    // User can move back and forth to any other connected room
    // 25% chance of finding secret room but once found can always find it.
    // When user exits the house or quits 25% chance followed by ghost.
    // Let user know if being followed by a ghost.
    // Also let the user know how many rooms they visited after quit.
    static int curRoom = 0;
    static ArrayList<String> travelList = new ArrayList<>();  // List of rooms user has been in
    static Scanner sc = new Scanner(System.in);
    static int[] directionArray = {0, 0, 0, 0};    // Available directions: North, South, East, West => set rooms avail for direction
    static Random rnd = new Random();
    public static void main(String[] args) {
        // main used to get input from user and direct user to appropriate room
        HashMap<Integer, String> houseMap = new HashMap<Integer, String>();
        houseMap.put(1, "Foyer");
        houseMap.put(2, "Front Room");
        houseMap.put(3, "Library");
        houseMap.put(4, "Kitchen");
        houseMap.put(5, "Dining Room");
        houseMap.put(6, "Vault");
        houseMap.put(7, "Parlor");
        houseMap.put(8, "Secret Room");
        String userResponse = "";
        String curRoomStr = "";
        String methodRet = "";
        String availDirections = "";  // North, South, East or West
        //int[] directionArray = {0, 0, 0, 0};    // Available directions: North, South, East, West => set rooms avail for direction
        int directionChosen = 0;

        while (true){
            if (curRoom == 0){
                System.out.println("Would you like to explore the mansion? (Y/N)");
                String val = sc.nextLine();
                if (val.equalsIgnoreCase("y")){
                    System.out.println("You have entered the Foyer.");
                    curRoom = 1;
                    curRoomStr = houseMap.get(1);
                    methodRet = foyer();
                }
                else{
                    System.out.println("You have decided not to explore the Mansion.");
                    break;
                }
            } else {
                System.out.println("Press (q) to quit or any other key to continue");
                String cont = sc.nextLine();
                if (cont.equalsIgnoreCase("q")) {
                    System.out.println("Thanks for playing.");
                    System.out.println("Your travel summary: "+ travelList);
                    int randomInt = rnd.nextInt(100);
                    if (randomInt <= 25){
                        System.out.println("You are being followed by a ghost");
                    }
                    break;
                }

                // e.g., Would you like to go (N)orth to the Front Room
                System.out.println(methodRet);
                String val = sc.nextLine();
                if (val.equalsIgnoreCase("n")) {
                    directionChosen = 0;
                } else
                if (val.equalsIgnoreCase("s")) {
                    directionChosen = 1;
                } else
                if (val.equalsIgnoreCase("e")) {
                    directionChosen = 2;
                } else
                if (val.equalsIgnoreCase("w")) {
                    directionChosen = 3;
                } else {
                    // Error
                    break;
                }

                // switch(chosenRoom) {
                switch (directionArray[directionChosen]){
                    case 1:
                        methodRet = foyer();
                        break;
                    case 2:
                        methodRet = frontRoom();
                        break;
                    case 3:
                        methodRet = library();
                        break;
                    case 4:
                        methodRet = kitchen();
                        break;
                    case 5:
                        methodRet = diningRm();
                        break;
                    case 6:
                        methodRet = vault();
                        break;
                    case 7:
                        methodRet = parlor();
                        break;
                    case 8:
                        methodRet = secretRm();
                        break;
                    default:
                        // An error has occurred
                        System.out.println("An Invalid Room number has occurred.");
                        break;
                }
            }
        }
    }

    public static String foyer(){
        // Room 1
        curRoom = 1;
        String out = "";
        String contains = "Dead Scorpion";
        System.out.println("You are in the Foyer.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (N)orth to the Front Room?";
        travelList.add("Foyer"); // Foyer
        //directionArray = {0, 0, 0, 0}; does not work
        directionArray = new int[]{ 2,0,0,0 }; // Can go north to room 2
        return out;
    }

    public static String frontRoom(){
        // Room 2
        curRoom = 2;
        String out = "";
        travelList.add("Front Room");
        String contains = "Piano";
        System.out.println("You are in the Front Room.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (W)est to the Library or (E)ast to the Kitchen?";
        directionArray = new int[]{ 0,0,4,3 };
        return out;
    }

    public static String library(){
        // Room 3
        curRoom = 3;
        String out = "";
        travelList.add("Library");
        String contains = "Spiders";
        System.out.println("You are in the Library.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (N)orth to the Dining Room or (E)ast to the Front Room";
        travelList.add("Library");
        directionArray = new int[]{ 5,0,2,0 };
        return out;
    }

    public static String kitchen(){
        // Room 4
        curRoom = 4;
        String out = "";
        travelList.add("Kitchen");
        String contains = "Bats";
        System.out.println("You are in the Kitchen.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (N)orth to the Parlor or (W)est to the Front Room.?";
        directionArray = new int[]{ 7,0,0,2 };
        return out;
    }

    public static String diningRm(){
        // Room 5
        curRoom = 5;
        String out = "";
        travelList.add("Dining Room");
        String contains = "Dust and Empty Box";
        System.out.println("You are in the Dining Room.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (S)outh to the Library.?";
        directionArray = new int[]{ 0,3,0,0 };
        return out;
    }

    public static String vault(){
        // Room 6
        // Add 25% probability of finding the secret room
        curRoom = 6;
        String out = "";
        travelList.add("Vault");
        String contains = "3 Walking Skeletons";
        System.out.println("You are in the Vault.");
        System.out.println("This room contains: " + contains);
        int randomInt = rnd.nextInt(100);
        if (randomInt > 25){
            directionArray = new int[]{ 0,0,7,0 };
            out = "Would you like to go (E)ast to the Parlor?";
        }else {
            directionArray = new int[]{ 0,0,8,0 };
            out = "Would you like to go (E)ast to the Secret Room?";
        }
        return out;
    }

    public static String parlor(){
        // Room 7
        curRoom = 7;
        String out = "";
        travelList.add("Parlor");
        String contains = "Treasure Chest";
        System.out.println("You are in the Parlor.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (S)outh to the Library (W)est to the Vault?";
        directionArray = new int[]{ 0,4,0,6 };
        return out;
    }

    public static String secretRm(){
        // Room 8
        curRoom = 8;
        String out = "";
        String contains = "Piles of Gold";
        travelList.add("Secret Room");
        System.out.println("You are in the Secret Room.");
        System.out.println("This room contains: " + contains);
        out = "Would you like to go (W)est to the Vault?";
        directionArray = new int[]{ 0,0,0,6 };
        return out;
    }
}
