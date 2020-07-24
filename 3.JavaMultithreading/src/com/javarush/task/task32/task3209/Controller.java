package com.javarush.task.task32.task3209;

import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class Controller {
    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public static void main(String... args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
    }

    public void init() {
        createNewDocument();
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public String getPlainText() {
        StringWriter sw = new StringWriter();
        try {
            new HTMLEditorKit().write(sw, document, 0, document.getLength());
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }

        return sw.toString();
    }

    public void setPlainText(String text) {
        resetDocument();

        StringReader sr = new StringReader(text);
        try {
            new HTMLEditorKit().read(sr, document, 0);
        } catch (IOException | BadLocationException e) {
            ExceptionHandler.log(e);
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
    }

    public void saveDocument() {
    }

    public void saveDocumentAs() {
    }

    public void openDocument() {
    }
}
