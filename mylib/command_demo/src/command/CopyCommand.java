package command;

import app.Application;
import data.Editor;

public class CopyCommand extends AbstractCommand {
    CopyCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public boolean execute(){
        app.setClipboard(editor.getSelection());
        return false;
    }
}
