public class FactoryMethod {
    public static void main(String[] args) {
        Dialog dialog1 = new WindowsDialog();
        dialog1.render();

        Dialog dialog2 = new WebDialog();
        dialog2.render();

        Dialog dialog3 = new Dialog();
        dialog3.render();
    }
}

class Dialog {
    public Button createButton() {
        return new DefaultButton();
    }

    public void render() {
        createButton().render();
    }
}

class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

class WebDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WebButton();
    }
}

interface Button {
    public void render();
}

class WindowsButton implements Button{
    public void render(){
        System.out.println("created windows button");
    }
}

class DefaultButton implements Button{
    public void render(){
        System.out.println("created default button");
    }
}

class WebButton implements Button{
    public void render(){
        System.out.println("created web button");
    }
}