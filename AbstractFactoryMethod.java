public class AbstractFactoryMethod {
    public static void main(String[] args) {
        GUIFactory factory1 = new WindowsFactory();
        App app1 = new App(factory1);
        app1.render();

        GUIFactory factory2 = new MacFactory();
        App app2 = new App(factory2);
        app2.render();
    }
}

class App {
    private GUIFactory factory;

    App(GUIFactory factory) {
        this.factory = factory;
    }

    public void render() {
        Button but1 = this.factory.createButton();
        but1.render();
    }
}

interface GUIFactory {
    Button createButton();
}

class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

interface Button {
    public void render();
}

class WindowsButton implements Button {
    public void render() {
        System.out.println("created windows button");
    }
}

class MacButton implements Button {
    public void render() {
        System.out.println("created mac button");
    }
}