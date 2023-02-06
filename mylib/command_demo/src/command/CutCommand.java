package command;

import app.Application;
import data.Editor;

public class CutCommand extends AbstractCommand {

    CutCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        saveBackup();
        app.setClipboard(editor.getSelection());
        editor.deleteSelection();

        return true;
    }
}
