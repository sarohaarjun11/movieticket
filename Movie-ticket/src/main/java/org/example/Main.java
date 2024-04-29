import org.example.Tickets;

import java.util.Scanner;

import static org.example.Customer.customer;

public class Main extends Tickets {
    static int ch=0;
    public static void start() {
        Scanner in = new Scanner(System.in);
        System.out.print("1: Tickets purchase\n2: Movies Info\n3: Customer details\nExit(0)\nEnter your choice: ");
        ch = in.nextInt();
        System.out.println();

        switch (ch) {
            case 1 -> {
                tickets();
                start();
            }
            case 2 -> {
                movies();
                start();
            }
            case 3 -> {
                customer();
                start();
            }
        }
        in.close();
    }

    public static void main(String[] args){
        start();
    }
}