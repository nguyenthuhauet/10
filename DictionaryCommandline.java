
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
public class DictionaryCommandline {

    public static void showAllWords() {
        System.out.println("No   | English\t|Vietnamese");
        for (int i = 0; i < Dictionary.a.size(); i++) {

            System.out.println(i + 1 + " |" + Dictionary.a.get(i).getWord()
                    + "\t \t|" + "" + Dictionary.a.get(i).getExplain());

        }
    }

    public static void dictionaryBasic() {
        DictionaryManagement.insertFromCommandline();
        showAllWords();
    }
    public static void dictionaryAdvanced() {
        DictionaryManagement.insertFromFile();
        showAllWords();
        DictionaryManagement.dictionaryLookup();
    }

}
