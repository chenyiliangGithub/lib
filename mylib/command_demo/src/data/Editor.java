package data;

/**
 *  编辑器类包含实际的文本编辑操作。它会担任接收者的角色：
 *  最后所有命令都会将执行工作委派给编辑器的方法。
 */
public class Editor {
    private String text;

    public String getSelection(){
       return "getSelection";
    }

    public void deleteSelection(){
        System.out.println("deleteSelection");
    }

    public void replaceSelection(String text){
        System.out.println("replaceSelection with" + text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
