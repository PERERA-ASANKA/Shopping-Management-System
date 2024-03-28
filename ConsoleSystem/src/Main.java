import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        while (true) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println();
                System.out.println();
                System.out.println("Enter 1 for User login or register and Enter 2 for Manager login: ");
                int num = scanner.nextInt();             // add user login or manger login
                if (num == 1) {
                    User user = new User();
                    user.loadFromFileUser();
                    Scanner user_credentials;
                    int input_option;

                    while (true) {
                        while (true) {
                            try {
                                System.out.println("1. Register");
                                System.out.println("2. Login");
                                System.out.print("Enter the option: ");
                                user_credentials = new Scanner(System.in);        //when user login first register or directly login
                                input_option = user_credentials.nextInt();
                                user_credentials.nextLine();
                                if (input_option > 2 || input_option < 1) {
                                    System.out.println("Enter the correct number of option.");
                                } else {
                                    break;
                                }
                            } catch (Exception InputMismatchException) {
                                System.out.println("Invalid input");
                            }
                        }
                        switch (input_option) {
                            case 1 -> {
                                System.out.print("Enter username: ");
                                String username_registering = user_credentials.nextLine();      //check registration details
                                System.out.print("Enter password: ");
                                String password_registering = user_credentials.nextLine();
                                user.userRegistration(username_registering, password_registering);
                                System.out.println("Access given to system");
                            }
                            case 2 -> {
                                System.out.print("Enter username: ");
                                String username_registered = user_credentials.nextLine();
                                System.out.print("Enter password: ");                         // check login details
                                String password_registered = user_credentials.nextLine();
                                user.UserLogin(username_registered, password_registered);
                                System.out.println("Access given to system");
                            }
                        }
                        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
                        int choice;
                        boolean status = true;
                        while (status) {
                            shoppingManager.display_menu();
                            while (true) {
                                try {
                                    Scanner input= new Scanner(System.in);
                                    System.out.println("Enter the option: ");        // get an input what should do in menu
                                    choice = input.nextInt();
                                    input.nextLine();
                                    if (choice > 6 || choice < 0) {
                                        System.out.println("Enter the correct number of option.");
                                    } else {
                                        break;
                                    }
                                } catch (Exception InputMismatchException) {
                                    System.out.println("Invalid input");
                                }
                            }
                            switch (choice) {    // switch case for user usage
                                case 1, 2, 3 -> System.out.println("Only manager can use this");
                                case 4 -> user.saveToFileUser();
                                case 5 -> user.loadFromFileUser();
                                case 6 -> shoppingManager.openGUI();
                                default -> {
                                    user.saveToFileUser();
                                    System.out.println("Thank you for using the system.");
                                    status = false;
                                }
                            }
                        }
                        break;
                    }
                    break;
                } else if (num==2) {
                    Scanner manager_password;
                    final String managerPassword = "Asanka20221405";  //check manager password

                    System.out.print("Enter manager password: ");
                    manager_password = new Scanner(System.in);
                    String entered_password = manager_password.nextLine();

                    if (entered_password.equals(managerPassword)) {
                        System.out.println("Manager login successful.");
                        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
                        shoppingManager.loadFromFile();
                        int choice;
                        boolean status = true;
                        while (status) {
                            shoppingManager.display_menu();
                            while (true) {
                                try {
                                    Scanner input = new Scanner(System.in);
                                    System.out.println("Enter the option: ");
                                    choice = input.nextInt();                        // get an input what should do in menu
                                    input.nextLine();
                                    if (choice > 6 || choice < 0) {
                                        System.out.println("Enter the correct number of option.");
                                    } else {
                                        break;
                                    }
                                } catch (Exception InputMismatchException) {
                                    System.out.println("Invalid input");
                                }
                            }
                            switch (choice) {       // switch case for manager usage
                                case 1 -> shoppingManager.addProduct();
                                case 2 -> shoppingManager.removeProduct();
                                case 3 -> shoppingManager.printProducts();
                                case 4 -> shoppingManager.saveToFile();
                                case 5 -> shoppingManager.loadFromFile();
                                case 6 -> shoppingManager.openGUI();
                                default -> {
                                    shoppingManager.saveToFile();
                                    System.out.println("Thank you for using the system.");
                                    status = false;
                                }
                            }
                        }
                        break;
                    } else {
                        System.out.println("Invalid Password!");
                    }
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input");
                break;
            }
        }


    }
}