
import java.util.Scanner;
import java.io.*;

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
            System.out.println("English : ");
            word = sc.nextLine();
            System.out.println("Vietnamese : ");
            String explain = sc.nextLine();
            Dictionary.a.add(new Word(word, explain));

        }

    }

    public static void insertFromFile() {
        try {
            FileReader fr = new FileReader("C:\\Users\\Administrator\\Desktop\\OOP\\dictionaries.txt");

            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWord;
                lineWord = line.split("\t");
                if (lineWord.length == 2) {
                    Dictionary.a.add(new Word(lineWord[0], lineWord[1]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void dictionaryLookup() {
        System.out.println("English :");
        Scanner sc = new Scanner(System.in);
        String wordLookup = sc.nextLine();
        for (int i = 0; i < Dictionary.a.size(); i++) {
            if (wordLookup.equals(Dictionary.a.get(i).getWord())) {
                System.out.println(Dictionary.a.get(i).getWord()
                        + "\t" + Dictionary.a.get(i).getExplain());
                return;
            }
            
        }
        System.out.println("No result!");
    }
}
