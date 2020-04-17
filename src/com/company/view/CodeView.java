package com.company.view;
import javafx.scene.layout.*;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;

public class CodeView {
    private CodeArea codeArea;
    public CodeView() {

    }

    public StackPane generateCodeView() {
        codeArea = new CodeArea();
        codeArea.replaceText(0, 0, "Dit werkt!");
        return new StackPane(new VirtualizedScrollPane<>(codeArea));

    }
    public void editText(String text) {
        codeArea.replaceText(0, 0, text);
    }
}
