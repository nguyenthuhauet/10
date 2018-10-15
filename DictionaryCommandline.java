
import java.io.*;
import java.util.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class DictionaryCommandline {

    private DictionaryManagement dictionaryMana;

    public DictionaryCommandline() {
        this.dictionaryMana = new DictionaryManagement();
    }

    public static void showAllWords() {
        System.out.println("No   | English\t|Vietnamese");
        for (int i = 0; i < Dictionary.a.size(); i++) {

            System.out.println(i + 1 + " |" + Dictionary.a.get(i).getTarget()
                    + "\t \t|" + "" + Dictionary.a.get(i).getExplain());

        }
    }

    public void dictionaryBasic() {
        dictionaryMana.insertFromCommandline();
        showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        dictionaryMana.insertFromFile();
        showAllWords();
        dictionaryMana.dictionaryLookup();
        System.out.println("Enter the number to:\n\t1.Delete word.\n\t2.Add word."
                + "\n\t3.Edit word.");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        if (num == 1) {
            dictionaryMana.deleteWord();
            return;
        }

        if (num == 2) {
            dictionaryMana.addWord();
            return;
        }
        if (num == 3) {
            dictionaryMana.editWord();
            return;
        }
        dictionaryMana.dictionaryExportToFile();

    }

    public void dictionarySeacher() {
        ArrayList<Word> List = dictionaryMana.getA();
        System.out.println("Search word : ");
        Scanner sc = new Scanner(System.in);
        String wordSearch = sc.nextLine();
        for (int i = 0; i < List.size(); i++) {
            if (List.get(i).getTarget().startsWith(wordSearch)) {
                System.out.println(List.get(i).getTarget() + "\t" + List.get(i).getExplain());
                return;
            }
        }
        System.out.println("No result!");

    }
}
