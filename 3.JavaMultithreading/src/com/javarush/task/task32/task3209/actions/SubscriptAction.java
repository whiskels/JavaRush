package com.javarush.task.task32.task3209.actions;

import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;

public class SubscriptAction extends StyledEditorKit.StyledTextAction {
    public SubscriptAction() {
        super(TextAttribute.SUPERSCRIPT_SUB.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
