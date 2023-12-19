import components.stack.Stack;

/**
 * Controller class.
 *
 * @author Bruce W. Weide
 * @author Paolo Bucci
 */
public final class AppendUndoController1 implements AppendUndoController {

    /**
     * Model object.
     */
    private final AppendUndoModel model;

    //boolean undoAllowed = true;
    /**
     * View object.
     */
    private final AppendUndoView view;

    /**
     * Updates view to display model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     */
    private static void updateViewToMatchModel(AppendUndoModel model,
            AppendUndoView view) {
        /*
         * Get model info
         */
        String input = model.input();
        Stack<String> output = model.output();
        /*
         * Update view to reflect changes in model
         */

        //update input
        view.updateInputDisplay(input);
        //update output and undo
        String outputString = "";
        Stack<String> outputCopy = output.newInstance();
        view.updateUndoAllowed(!(output.length() == 0));
        int len = output.length();
        for (int i = 0; i < len; i++) {
            String s = output.pop();
            outputCopy.push(s);
        }
        for (int i = 0; i < len; i++) {
            String t = outputCopy.pop();
            outputString += t;
            output.push(t);
        }
        view.updateOutputDisplay(outputString);

    }

    /**
     * Constructor; connects {@code this} to the model and view it coordinates.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public AppendUndoController1(AppendUndoModel model, AppendUndoView view) {
        this.model = model;
        this.view = view;
        /*
         * Update view to reflect initial value of model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    /**
     * Processes reset event.
     */
    @Override
    public void processResetEvent() {
        /*
         * Update model in response to this event
         */
        //Stack<String> outputCopy = this.model.output().newInstance();

        this.model.setInput("");
        this.model.output().clear();

        /*
         * Update view to reflect changes in model
         */

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAppendEvent(String input) {
        Stack<String> output = this.model.output();
        output.push(input);

        this.model.setInput("");
        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processUndoEvent() {
        String input = this.model.output().pop();
        this.model.setInput(input);
        updateViewToMatchModel(this.model, this.view);
    }

}
