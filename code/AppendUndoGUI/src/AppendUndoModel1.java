import components.stack.Stack;
import components.stack.Stack1L;

/**
 * Model class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoModel1 implements AppendUndoModel {

    /**
     * Model variables.
     */
    private String input;
    Stack<String> output;

    /**
     *
     * Default constructor.
     */
    public AppendUndoModel1() {
        /*
         * Initialize model; both variables start as empty strings
         */
        this.input = "";
        this.output = new Stack1L<>();
    }

    @Override
    public String input() {
        // TODO Auto-generated method stub
        return this.input;
    }

    @Override
    public void setInput(String input) {
        // TODO Auto-generated method stub
        this.input = input;
    }

    @Override
    public Stack<String> output() {
        // TODO Auto-generated method stub
        return this.output;
    }

}
