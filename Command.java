import java.util.*;

public class Command {
    public static void main(String[] args) {
        Application app = new Application();
        Editor editor = new Editor();
        Command_t copy = new CopyCommand(app, editor);
        Command_t cut = new CutCommand(app, editor);
        Command_t paste = new PasteCommand(app, editor);
        Command_t undo = new UndoCommand(app, editor);

        editor.text = "ciao1234ciao";
        System.out.println(editor.text);

        app.executeCommand(copy, 4, 8);
        app.executeCommand(paste, 0, 0);
        System.out.println(editor.text);

        app.executeCommand(undo, 0, 0);
        System.out.println(editor.text);

        app.executeCommand(cut, 4, 8);
        app.executeCommand(paste, 0, 0);
        System.out.println(editor.text);

        app.executeCommand(undo, 0, 0);
        System.out.println(editor.text);

        app.executeCommand(cut, 1, 6);
        System.out.println(editor.text);

        app.executeCommand(undo, 0, 0);
        System.out.println(editor.text);
    }
}

abstract class Command_t {
    protected Application app;
    protected Editor editor;
    protected String backup;

    Command_t(Application app, Editor editor) {
        this.app = app;
        this.editor = editor;
    }

    public void saveBackup() {
        backup = editor.text;
    }

    public void undo() {
        editor.text = backup;
    }

    public abstract boolean execute(int begin, int end);
}

class CopyCommand extends Command_t {
    CopyCommand(Application app, Editor editor) {
        super(app, editor);
    }

    public boolean execute(int begin, int end) {
        app.clipboard = editor.getSelection(begin, end);
        return false;
    }
}

class CutCommand extends Command_t {
    CutCommand(Application app, Editor editor) {
        super(app, editor);
    }

    public boolean execute(int begin, int end) {
        saveBackup();
        app.clipboard = editor.getSelection(begin, end);
        editor.deleteSelection(begin, end);
        return true;
    }
}

class PasteCommand extends Command_t {
    PasteCommand(Application app, Editor editor) {
        super(app, editor);
    }

    public boolean execute(int begin, int end) {
        saveBackup();
        editor.replaceText(app.clipboard);
        return true;
    }
}

class UndoCommand extends Command_t {
    UndoCommand(Application app, Editor editor) {
        super(app, editor);
    }

    public boolean execute(int begin, int end) {
        app.undo();
        return false;
    }
}

class CommandHistory {
    private Stack<Command_t> history = new Stack<Command_t>();

    public void push(Command_t c) {
        history.push(c);
    }

    public Command_t pop() {
        return history.pop();
    }
}

class Editor {
    public String text;

    public String getSelection(int begin, int end) {
        return text.substring(begin, end);
    }

    public void deleteSelection(int begin, int end) {
        text = text.substring(0, begin) + text.substring(end);
    }

    public void replaceText(String text) {
        this.text = this.text.concat(text);
    }
}

class Application {
    public String clipboard;
    private CommandHistory history = new CommandHistory();

    public void executeCommand(Command_t command, int begin, int end) {
        if (command.execute(begin, end))
            history.push(command);
    }

    public void undo() {
        Command_t command = history.pop();
        if (command != null)
            command.undo();
    }
}