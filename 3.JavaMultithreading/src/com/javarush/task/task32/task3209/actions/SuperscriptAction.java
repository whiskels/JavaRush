package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super(TextAttribute.SUPERSCRIPT.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
