package command;

import app.Application;
import data.Editor;

public class PasteCommand extends AbstractCommand{
    PasteCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        saveBackup();
        editor.replaceSelection(app.getClipboard());
        return true;
    }
}
