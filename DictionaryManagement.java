
import java.util.*;
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

    private static Dictionary dictionary;

    public DictionaryManagement() {
        dictionary = new Dictionary();
    }

    public ArrayList<Word> getA() {
        return dictionary.getWord();
    }

    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Number :");

        int n = sc.nextInt();

        String word = sc.nextLine();
        for (int i = 0; i < n; i++) {
            System.out.println("English : ");
            word = sc.nextLine();
            System.out.println("Vietnamese : ");
            String explain = sc.nextLine();
            getA().add(new Word(word, explain));

        }

    }

    public void insertFromFile() throws IOException {

        try {
            FileReader fr = new FileReader("C:\\Users\\Administrator\\Desktop\\OOP\\Main\\src\\dictionaries\\dictionaries.txt");

            BufferedReader br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineWord;
                lineWord = line.split("\t");
                if (lineWord.length == 2) {
                    getA().add(new Word(lineWord[0], lineWord[1]));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void dictionaryLookup() {
        System.out.println("English :");
        Scanner sc = new Scanner(System.in);
        String wordLookup = sc.nextLine();
        for (int i = 0; i < getA().size(); i++) {
            if (wordLookup.equals(getA().get(i).getTarget())) {
                System.out.println(getA().get(i).getTarget()
                        + "\t" + getA().get(i).getExplain());
                return;
            }

        }
        System.out.println("No result!");

    }

    public void addWord() {
        System.out.println("Add word : ");
        Scanner sc = new Scanner(System.in);
        String targetAdd = sc.nextLine();
        System.out.println("Add explain : ");
        String explainAdd = sc.nextLine();
        Word wordAdd = new Word(targetAdd, explainAdd);
        getA().add(wordAdd);
    }

    public void deleteWord() {
        System.out.println("Delete word : ");
        Scanner sc = new Scanner(System.in);
        String wordDel = sc.nextLine();
        for (int i = 0; i < Dictionary.a.size(); i++) {
            if (wordDel.equals(dictionary.a.get(i).getTarget())) {
                getA().remove(new Word(wordDel, getA().get(i).getExplain()));
                System.out.println("Successfully!");
                return;
            }

        }
        System.out.println("No result!");
    }

    public void editWord() {
        System.out.println("Edit word : ");
        Scanner sc = new Scanner(System.in);
        String targetEdit = sc.nextLine();
        System.out.println("Edit explain : ");
        String explainEdit = sc.nextLine();
        for (int i = 0; i < getA().size(); i++) {
            if (explainEdit.equals(getA().get(i).getTarget())) {
                getA().get(i).word_explain = explainEdit;
                return;
            }
        }
        System.out.println("No result!");
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("C:\\Users\\Administrator\\Desktop\\OOP\\Main\\src\\export\\export.txt");
            BufferedWriter bw = new BufferedWriter(fw);
          
            for (int i = 0; i < getA().size(); i++) {
                bw.write(getA().get(i).getTarget() + "\t" + getA().get(i).getExplain());
                bw.newLine();

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
