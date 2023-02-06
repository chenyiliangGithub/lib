package command;

import app.Application;
import data.Editor;

import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractCommand {
    // protected 权限
    protected Application app; // 命令创建者
    protected Editor editor; // 命令接收者
    protected String backup; // 备忘录模式

    AbstractCommand(Application app, Editor editor){
        this.app = app;
        this.editor = editor;
    }

    // 备份编辑器内容
    public void saveBackup(){
        backup = editor.getText();
    }

    // 恢复编辑器内容
    public void undo(){
        editor.setText(backup);
    }

    // 执行方法被声明为抽象以强制所有具体命令提供自己的实现。
    // 该方法必须根据命令是否更改了编辑器的内容返回 true 或 false。该返回值用于记录备忘录
    public abstract boolean execute();
}
