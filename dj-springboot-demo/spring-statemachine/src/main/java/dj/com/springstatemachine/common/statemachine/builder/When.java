
package dj.com.springstatemachine.common.statemachine.builder;

import dj.com.springstatemachine.common.statemachine.Action;

public interface When<S, E, C>{
    /**
     * Define action to be performed during transition
     *
     * @param action performed action
     */
    void perform(Action<S, E, C> action);
}
