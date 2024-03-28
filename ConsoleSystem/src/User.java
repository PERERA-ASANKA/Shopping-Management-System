import java.io.*;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.Scanner;

public class User implements Serializable{
    private String user_name;      // assign user name
    private String user_password;      // assign user password
    private ArrayList<User> users;       // create array

    public User(String user_name,String user_password){    // create a constructor
        this.user_name = user_name;
        this.user_password = user_password;
    }
    public User(){
        this.users = new ArrayList<>();
    }

    public String getUser_name(){
        return user_name;
    }
    public void setUser_name(String user_name){
        this.user_name = user_name;
    }
    public String getUser_password(){
        return user_password;
    }
    public void setUser_password(String user_password){
        this.user_password = user_password;
    }
    public boolean userRegistration(String user_name,String user_password) {    // create a method to userRegistration
            while(checkUsernameTaken(user_name)) {       // check username has been already taken or not
                System.out.println("Cannot use as User name because it has already taken, Enter another User name");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter another username: ");
                user_name = scanner.nextLine();
            }
                User newUser = new User(user_name, user_password);
                users.add(newUser);
                System.out.println("Successfully register for: " + user_name);
                return true;
        }

    private boolean checkUsernameTaken(String user_name) {  // check username taking as a method
        for (User user : users) {
            if (user.getUser_name().equals(user_name)) {
                return true;
            }
        }
        return false;
    }
    public boolean UserLogin(String user_name, String user_password) {  // create a user login method
        while (true) {
            for (User user : users) {
                if (user.getUser_name().equals(user_name) && user.getUser_password().equals(user_password)) {
                    System.out.println("Successfully logged in as: " + user_name);
                    return true;                                       //check whether username and password have been already taken or not
                }
            }
            System.out.println("Invalid username or password.");
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter another username (or type 'exit' to cancel): ");
            user_name = scanner.nextLine();
            if ("exit".equalsIgnoreCase(user_name)) {
                System.out.println("Login canceled.");
                return false;
            }
            System.out.print("Enter password: ");
            user_password = scanner.nextLine();
        }
    }

    public void saveToFileUser() {     // save user details
        File console_user_file = new File("system_user_login.dat");
        try {
            FileOutputStream fileOutputStream_user = new FileOutputStream(console_user_file);
            ObjectOutputStream stream_of_output = new ObjectOutputStream(fileOutputStream_user);
            stream_of_output.writeObject(users);
            stream_of_output.close();
        } catch (Exception e) {
            System.out.println("Have a error" + e);
        }
    }
    public  void loadFromFileUser(){   // load the save details
        try (ObjectInputStream stream_of_input_user = new ObjectInputStream(new FileInputStream("system_user_login.dat"))) {
            try {
                ArrayList<User> get_detailsArray_user = (ArrayList<User>) stream_of_input_user.readObject();
                stream_of_input_user.close();
                users = get_detailsArray_user;

            } catch (Exception e) {
                System.out.println("Have a error:"+e);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
