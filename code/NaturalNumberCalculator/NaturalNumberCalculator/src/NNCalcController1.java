import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Controller class.
 *
 * @author Mohammed Maalin
 */
public final class NNCalcController1 implements NNCalcController {

    /**
     * Model object.
     */
    private final NNCalcModel model;

    /**
     * View object.
     */
    private final NNCalcView view;

    /**
     * Useful constants.
     */
    private static final NaturalNumber TWO = new NaturalNumber2(2),
            INT_LIMIT = new NaturalNumber2(Integer.MAX_VALUE);

    /**
     * Updates this.view to display this.model, and to allow only operations
     * that are legal given this.model.
     *
     * @param model
     *            the model
     * @param view
     *            the view
     * @ensures [view has been updated to be consistent with model]
     */
    private static void updateViewToMatchModel(NNCalcModel model,
            NNCalcView view) {

        view.updateBottomDisplay(model.bottom());
        view.updateTopDisplay(model.top());

        //update allowed
        if (model.bottom().isZero()) {
            view.updateDivideAllowed(false);
        } else {
            view.updateDivideAllowed(true);
        }
        if (model.bottom().compareTo(model.top()) > 0) {
            view.updateSubtractAllowed(false);
        } else {
            view.updateSubtractAllowed(true);
        }
        if (model.bottom().compareTo(TWO) < 0
                && model.bottom().compareTo(INT_LIMIT) > 0) {
            view.updatePowerAllowed(false);
            view.updateRootAllowed(false);
        } else {
            view.updatePowerAllowed(true);
            view.updateRootAllowed(true);
        }
    }

    /**
     * Constructor.
     *
     * @param model
     *            model to connect to
     * @param view
     *            view to connect to
     */
    public NNCalcController1(NNCalcModel model, NNCalcView view) {
        this.model = model;
        this.view = view;
        updateViewToMatchModel(model, view);
    }

    @Override
    public void processClearEvent() {
        /*
         * Get alias to bottom from model
         */
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        bottom.clear();
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSwapEvent() {
        /*
         * Get aliases to top and bottom from model
         */
        NaturalNumber top = this.model.top();
        NaturalNumber bottom = this.model.bottom();
        /*
         * Update model in response to this event
         */
        NaturalNumber temp = top.newInstance();
        temp.transferFrom(top);
        top.transferFrom(bottom);
        bottom.transferFrom(temp);
        /*
         * Update view to reflect changes in model
         */
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processEnterEvent() {

        this.model.top().transferFrom(this.model.bottom());
        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processAddEvent() {
        NaturalNumber bottom = new NaturalNumber2(this.model.bottom());
        NaturalNumber top = new NaturalNumber2(this.model.top());

        top.add(bottom);
        this.model.bottom().transferFrom(top);

        this.model.top().setFromInt(0);

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processSubtractEvent() {
        NaturalNumber bottom = new NaturalNumber2(this.model.bottom());
        NaturalNumber top = new NaturalNumber2(this.model.top());

        top.subtract(bottom);
        this.model.bottom().transferFrom(top);

        this.model.top().setFromInt(0);

        updateViewToMatchModel(this.model, this.view);
    }

    @Override
    public void processMultiplyEvent() {
        NaturalNumber bottom = new NaturalNumber2(this.model.bottom());
        NaturalNumber top = new NaturalNumber2(this.model.top());

        top.multiply(bottom);
        this.model.bottom().transferFrom(top);

        this.model.top().setFromInt(0);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processDivideEvent() {
        NaturalNumber bottom = new NaturalNumber2(this.model.bottom());
        NaturalNumber top = new NaturalNumber2(this.model.top());

        top.divide(bottom);
        this.model.bottom().transferFrom(top);

        this.model.top().setFromInt(0);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processPowerEvent() {

        NaturalNumber bottom = new NaturalNumber2(this.model.bottom());
        NaturalNumber top = new NaturalNumber2(this.model.top());

        top.power(bottom.toInt());
        this.model.bottom().transferFrom(top);

        this.model.top().setFromInt(0);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processRootEvent() {

        NaturalNumber bottom = new NaturalNumber2(this.model.bottom());
        NaturalNumber top = new NaturalNumber2(this.model.top());

        top.root(bottom.toInt());
        this.model.bottom().transferFrom(top);

        this.model.top().setFromInt(0);

        updateViewToMatchModel(this.model, this.view);

    }

    @Override
    public void processAddNewDigitEvent(int digit) {

        this.model.bottom().multiplyBy10(digit);

        updateViewToMatchModel(this.model, this.view);

    }

}
