package main;

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

    public int getSize() {
        return dictionary.a.size();
    }

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
            FileReader fr = new FileReader("dictionaries.txt");

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
        System.out.println("Không tìm thấy từ!");

    }
    public String dictionaryLookup(String wordLookup) {

        for (int i = 0; i < getA().size(); i++) {
            if (wordLookup.equals(getA().get(i).getTarget())) {
                System.out.println(getA().get(i).getTarget()
                        + "\t" + getA().get(i).getExplain());
                return dictionary.a.get(i).getExplain();
            }

        }
        return "Không tìm thấy từ!";

    }

    public void addWord() {
        System.out.println("Nhập từ cần thêm : ");
        Scanner sc = new Scanner(System.in);
        String targetAdd = sc.nextLine();
        System.out.println("Nhập nghĩa tiếng Việt : ");
        String explainAdd = sc.nextLine();
        Word wordAdd = new Word(targetAdd, explainAdd);
        getA().add(wordAdd);
    }
    public void deleteWord() {
        System.out.println("Nhập từ cần xóa : ");
        Scanner sc = new Scanner(System.in);
        String wordDel = sc.nextLine();
        for (int i = 0; i < Dictionary.a.size(); i++) {
            if (wordDel.equals(dictionary.a.get(i).getTarget())) {
                getA().remove(new Word(wordDel, getA().get(i).getExplain()));
                System.out.println("Xóa thành công!");
                return;
            }

        }
        System.out.println("Không tìm thấy từ cần xóa!");
    }

    public void editWord() {
        System.out.println("Nhập từ cần sửa : ");
        Scanner sc = new Scanner(System.in);
        String targetEdit = sc.nextLine();
        System.out.println("Nhập nghĩa của từ cần sửa : ");
        String explainEdit = sc.nextLine();
        for (int i = 0; i < getA().size(); i++) {
            if (explainEdit.equals(getA().get(i).getTarget())) {
                getA().get(i).word_explain = explainEdit;
                return;
            }
        }
        System.out.println("Không tìm thấy từ cần sửa!");
    }
    public String addWord(String wordTarget, String wordExplain) {
        for (int i = 0; i < getSize(); i++) {
            if (wordTarget.equals(dictionary.a.get(i).getTarget()));
            return "Từ cần thêm hiện đã tồn tại!";
        }
        Word wordAdd = new Word(wordTarget, wordExplain);
        dictionary.a.add(wordAdd);
        dictionaryExportToFile();
        return "";
    }

    public String deleteWord(String wordDel) {

        for (int i = 0; i < Dictionary.a.size(); i++) {
            if (wordDel.equals(dictionary.a.get(i).getTarget())) {
                getA().remove(new Word(wordDel, getA().get(i).getExplain()));
                dictionaryExportToFile();
                return "";
            }

        }
        return "Không tìm thấy từ cần xóa!";
    }

    public String editWord(String wordTarget, String wordExplain) {

        for (int i = 0; i < getA().size(); i++) {
            if (wordTarget.equals(dictionary.a.get(i).getTarget())) {
                dictionary.a.remove(i);
                dictionaryExportToFile();
                return "";
            }
        }
        return "Không tìm thấy từ cần sửa!";
    }

    public void dictionaryExportToFile() {
        try {
            FileWriter fw = new FileWriter("export.txt");
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < getSize(); i++) {
                bw.write(getA().get(i).getTarget() + "\t" + getA().get(i).getExplain());
                bw.newLine();

            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String dictionarySeacher(String word) {
        int countWord = 0;
        for (int i = 0; i < getSize(); i++) {
            if (dictionary.a.get(i).getTarget().startsWith(word)) {
                countWord++;
            }
            if (countWord != 0) {
                return "";
            }
        }
        return "Không tìm thấy từ";

    }

   

}
