import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("****************************************************");
        System.out.println("Welcome to Hospital Appointments Management System!");
        System.out.println("****************************************************");

        Scanner input =new Scanner(System.in);
        int UserChoice= 0;
        do{
            System.out.println("1- Login as Admin");
            System.out.println("2- Login as Doctor");
            System.out.println("3- Login as Patient");
            System.out.println("4- Exit");
            System.out.println("Enter a Number:");
            try{
                UserChoice=input.nextInt();
                input.nextLine();
                if(UserChoice<1 || UserChoice>4){
                    System.out.println("please Enter between (1-4)");
                }
            }
            catch (Exception e) {
                System.out.println("please Enter only a number !!");
                input.nextLine();
            }
            switch(UserChoice){
                //need work !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                case 1 ->System.out.println("method login admin");
                case 2-> System.out.println("method login Doctor");
                case 3-> System.out.println("method login Patient");
                case 4-> System.out.println("method  saving data");
                default -> System.out.println("");
                // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }

        }while(UserChoice != 4);

    }
}
