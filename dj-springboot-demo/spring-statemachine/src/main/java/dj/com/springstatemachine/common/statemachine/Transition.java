
package dj.com.springstatemachine.common.statemachine;

import dj.com.springstatemachine.common.statemachine.impl.TransitionType;

public interface Transition<S, E, C> {
    /**
     * Gets the source state of this transition.
     *
     * @return the source state
     */
    State<S,E,C> getSource();

    void setSource(State<S, E, C> state);

    E getEvent();

    void setEvent(E event);

    void setType(TransitionType type);
    /**
     * Gets the target state of this transition.
     *
     * @return the target state
     */
    State<S,E,C> getTarget();

    void setTarget(State<S, E, C> state);

    /**
     * Gets the guard of this transition.
     *
     * @return the guard
     */
    Condition<C> getCondition();

    void setCondition(Condition<C> condition);

    Action<S,E,C> getAction();

    void setAction(Action<S, E, C> action);

    /**
     * Do transition from source state to target state.
     *
     * @return the target state
     */

    State<S, E, C> transit(C ctx, boolean checkCondition);
    /**
     * Verify transition correctness
     */
    void verify();
}
