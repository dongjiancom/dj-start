
package dj.com.springstatemachine.common.statemachine.builder;


import dj.com.springstatemachine.common.statemachine.StateMachine;

public interface StateMachineBuilder<S, E, C> {
    /**
     * Builder for one transition
     * @return External transition builder
     */
    ExternalTransitionBuilder<S, E, C> externalTransition();

    /**
     * Builder for multiple transitions
     * @return External transition builder
     */
    ExternalTransitionsBuilder<S, E, C> externalTransitions();

    /**
     * Start to build internal transition
     * @return Internal transition builder
     */
    InternalTransitionBuilder<S, E, C> internalTransition();

    StateMachine<S,E,C> build(String machineId);
}
