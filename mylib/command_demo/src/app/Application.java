package app;

import command.AbstractCommand;
import command.CommandHistory;
import command.PasteCommand;
import data.Editor;

import java.util.List;

/**
 *  应用程序类会设置对象之间的关系。它会担任发送者的角色：
 *  当需要完成某些工作时，它会创建并执行一个命令对象。
 */
public class Application {
    private String clipboard;
    private List<Editor> editors;
    private CommandHistory history;


    // 执行一个命令并检查它是否需要被添加到历史记录中。
    void executeCommand(AbstractCommand command){
        if (command.execute()){
            history.push(command);
        }
    }

    // 从历史记录中取出最近的命令并运行其 undo（撤销）方法。请注意，你并
    // 不知晓该命令所属的类。但是我们不需要知晓，因为命令自己知道如何撤销
    // 其动作。
    public void undo(){
        AbstractCommand command = history.pop();
        if (command != null){
            command.undo();
        }
    }
    // 将命令分派给 UI 对象的代码可能会是这样的。
    // 以下代码可以用 策略模式 优化
//    method createUI() is
//        // ……
//        copy = function() { executeCommand(
//            new CopyCommand(this, activeEditor)) }
//        copyButton.setCommand(copy)
//        shortcuts.onKeyPress("Ctrl+C", copy)
//
//        cut = function() { executeCommand(
//            new CutCommand(this, activeEditor)) }
//        cutButton.setCommand(cut)
//        shortcuts.onKeyPress("Ctrl+X", cut)
//
//        paste = function() { executeCommand(
//            new PasteCommand(this, activeEditor)) }
//        pasteButton.setCommand(paste)
//        shortcuts.onKeyPress("Ctrl+V", paste)
//
//        undo = function() { executeCommand(
//            new UndoCommand(this, activeEditor)) }
//        undoButton.setCommand(undo)
//        shortcuts.onKeyPress("Ctrl+Z", undo)


    public String getClipboard() {
        return clipboard;
    }

    public void setClipboard(String clipboard) {
        this.clipboard = clipboard;
    }
}
