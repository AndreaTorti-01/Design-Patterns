public class State {
    public static void main(String[] args) {
        TVContext context = new TVContext();
        State_t tvStartState = new TVStartState();
        State_t tvStopState = new TVStopState();

        context.setState(tvStartState);
        context.doAction();
        System.out.println(context.getState());

        context.setState(tvStopState);
        context.doAction();
        System.out.println(context.getState());

        context.setState(tvStartState);
        context.doAction();
        System.out.println(context.getState());
    }
}

interface State_t {

    public void doAction();
}

class TVStartState implements State_t {

    @Override
    public void doAction() {
        System.out.println("TV is turned ON");
    }

}

class TVStopState implements State_t {

    @Override
    public void doAction() {
        System.out.println("TV is turned OFF");
    }

}

class TVContext implements State_t {

    private State_t tvState;

    public void setState(State_t state) {
        this.tvState = state;
    }

    public State_t getState() {
        return this.tvState;
    }

    @Override
    public void doAction() {
        this.tvState.doAction();
    }

}