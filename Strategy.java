public class Strategy {
    public static void main(String[] args) {
        Context context = new Context();

        context.setStrategy(new Add());
        System.out.println(context.executeStrategy(2, 3));

        context.setStrategy(new Sub());
        System.out.println(context.executeStrategy(4, 2));

        context.setStrategy(new Mult());
        System.out.println(context.executeStrategy(4, 3));
    }
}

interface Strategy_t {
    int execute(int a, int b);
}

class Add implements Strategy_t {
    @Override
    public int execute(int a, int b) {
        return a + b;
    }
}

class Sub implements Strategy_t {
    @Override
    public int execute(int a, int b) {
        return a - b;
    }
}

class Mult implements Strategy_t {
    @Override
    public int execute(int a, int b) {
        return a * b;
    }
}

class Context {
    private Strategy_t strategy;

    void setStrategy(Strategy_t strategy) {
        this.strategy = strategy;
    }

    int executeStrategy(int a, int b) {
        return strategy.execute(a, b);
    }
}