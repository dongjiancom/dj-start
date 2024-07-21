
package dj.com.springstatemachine.common.statemachine;

import dj.com.springstatemachine.common.statemachine.impl.TransitionType;

import java.util.Collection;
import java.util.List;

public interface State<S,E,C> {
    /**
     * Gets the state identifier.
     *
     * @return the state identifiers
     */
    S getId();

    String accept(Visitor visitor);

    /**
     * Add transition to the state
     * @param event the event of the Transition
     * @param target the target of the transition
     * @return
     */
    Transition<S,E,C> addTransition(E event, State<S, E, C> target, TransitionType transitionType);

    List<Transition<S,E,C>> getEventTransitions(E event);

    Collection<Transition<S,E,C>> getAllTransitions();
}
