
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class DictionaryManagement {

    public static void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number :");

        int n = sc.nextInt();

        String word = sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("English");
            word = sc.nextLine();
            System.out.println("Vietnamese");
            String explain = sc.nextLine();
            Dictionary.a.add(new Word(word, explain));

        }

    }

}

