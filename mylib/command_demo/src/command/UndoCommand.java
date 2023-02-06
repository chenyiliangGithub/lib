package command;

import app.Application;
import data.Editor;

public class UndoCommand extends AbstractCommand{
    UndoCommand(Application app, Editor editor) {
        super(app, editor);
    }

    @Override
    public boolean execute() {
        app.undo();
        return false;
    }
}
