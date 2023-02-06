package command;

import command.AbstractCommand;

import java.util.Stack;

public class CommandHistory {
    private Stack<AbstractCommand> history = new Stack<>();

    public void push(AbstractCommand command){
        history.push(command);
    }

    public AbstractCommand pop(){
        return history.pop();
    }
}
