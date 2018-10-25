package sample.src.sample;

import java.io.IOException;
import main.*;

import main.DictionaryManagement;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
//import com.sun.speech.freetts.VoiceManager;
//import com.gtranslate.Language;
//import com.gtranslate.Audio;
//import com.gtranslate.Translator;
//import javax.swing.JTextArea;

public class Controller implements Initializable {

    DictionaryManagement dict = new DictionaryManagement();
    ArrayList<Word> listWord = new ArrayList<Word>();

    String wordSearch;
    @FXML
    private TextField textField;
    @FXML
    private Button addWord;
    @FXML
    private Button updateWord;
    @FXML
    private Button deleteWord;
    @FXML
    private TextArea textArea;
    @FXML
    private ListView listView;

    public void updateListWord() {
        listWord = dict.getA();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            dict.insertFromFile();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateListView();
        textChange();
        selectWord();

    }

    public void selectWord() {
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                textArea.setText(dict.dictionaryLookup(newValue));
            }
        });
    }

    
    public void search(ActionEvent event) throws Exception {

        wordSearch = textField.getText();
        updateListWord();
        try {
            if (dict.dictionaryLookup(wordSearch) != "Không tìm thấy từ!") {
                ObservableList<String> items = FXCollections.observableArrayList();
                items.add(wordSearch);
                listView.setItems(items);
                textArea.setText(dict.dictionaryLookup(wordSearch));
                
            } else {
                notFoundWord();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

   

    public void notFoundWord() {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("NOT Found Word!");
        alert1.setHeaderText("Không tìm được từ!");
        alert1.show();
    }

    public void textChange() {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                wordSearch = newValue;
                ObservableList<String> items = FXCollections.observableArrayList();
                for (int i = 0; i < listWord.size(); i++) {
                    if (listWord.get(i).getTarget().startsWith(wordSearch)) {
                        items.add(listWord.get(i).getTarget());
                    }
                }
                listView.setItems(items);
            }
        });
        

    }

    public void updateListView() {
        listView.getItems().removeAll();
        listView.getItems().addAll();
        updateListWord();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < listWord.size(); i++) {
            items.add(listWord.get(i).getTarget());
        }
        listView.setItems(items);
    }

    public void dialogAdd() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Add Word");

        ButtonType buttonTypeAdd = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeAdd, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField wordTarget = new TextField();
        wordTarget.setPromptText("Word target");
        TextField wordExplain = new TextField();
        wordExplain.setPromptText("Word explain");

        grid.add(new Label("Word target"), 0, 0);
        grid.add(wordTarget, 1, 0);
        grid.add(new Label("Word explain"), 0, 1);
        grid.add(wordExplain, 1, 1);

        Node addButton = dialog.getDialogPane().lookupButton(buttonTypeAdd);
        addButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            addButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeAdd) {
                return new Pair<>(wordTarget.getText(), wordExplain.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(wordAdd -> {
            if (dict.addWord(wordAdd.getKey(), wordAdd.getValue()).equals("Đã có từ!")) {
                canNotAddWord();
            } else {
                dict.addWord(wordAdd.getKey(), wordAdd.getValue());
                done("thêm từ");
            }
        });
        updateListView();
    }

    public void canNotAddWord() {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Can Not Add Word");
        alert1.setHeaderText("Từ đã có !!");
        alert1.show();
    }

    public void done(String command) {
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        //alert1.setTitle("Can Not Add Word");
        alert1.setHeaderText("Đã " + command + " thành công !");
        alert1.show();
        updateListView();
    }

    public void dialogUpdate() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Update Word");

        ButtonType buttonTypeUpdate = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeUpdate, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField wordTarget = new TextField();
        wordTarget.setPromptText("Word target");
        TextField wordExplain = new TextField();
        wordExplain.setPromptText("Word explain");

        grid.add(new Label("Word target"), 0, 0);
        grid.add(wordTarget, 1, 0);
        grid.add(new Label("Word explain"), 0, 1);
        grid.add(wordExplain, 1, 1);

        Node updateButton = dialog.getDialogPane().lookupButton(buttonTypeUpdate);
        updateButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            updateButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeUpdate) {
                return new Pair<>(wordTarget.getText(), wordExplain.getText());
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(wordUpdate -> {
            if (dict.editWord(wordUpdate.getKey(), wordUpdate.getValue()).equals("Không tìm được từ cần sửa!")) {
                canNotAddWord();
            } else {
                dict.editWord(wordUpdate.getKey(), wordUpdate.getValue());
                done("sửa từ");
            }
        });
    }

    public void dialogDelete() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Delete Word");

        ButtonType buttonTypeDelete = new ButtonType("Delete", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeDelete, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField wordTarget = new TextField();
        wordTarget.setPromptText("Word target");

        grid.add(new Label("Word target"), 0, 0);
        grid.add(wordTarget, 1, 0);

        Node deleteButton = dialog.getDialogPane().lookupButton(buttonTypeDelete);
        deleteButton.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            deleteButton.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeDelete) {
                return new Pair<>(wordTarget.getText(), "");
            }
            return null;
        });
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(wordDelete -> {
            if (dict.deleteWord(wordDelete.getKey()).equals("Không tìm thấy từ cần xóa!")) {
                notFoundWord();
            } else {
                dict.deleteWord(wordDelete.getKey());
                done("xóa từ");
            }
            updateListView();
        });
    }

//    public static void speech(String text) {
//        VoiceManager voiceManager = VoiceManager.getInstance();
//        com.sun.speech.freetts.Voice syntheticVoice = voiceManager.getVoice("kevin16");
//        syntheticVoice.allocate();
//        syntheticVoice.speak(text);
//        syntheticVoice.deallocate();
//    }

}
