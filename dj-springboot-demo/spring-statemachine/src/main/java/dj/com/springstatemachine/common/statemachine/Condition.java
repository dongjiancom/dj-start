
package dj.com.springstatemachine.common.statemachine;

public interface Condition<C> {
    /**
     * @param context context object
     * @return whether the context satisfied current condition
     */
    boolean isSatisfied(C context);

    default String name(){
        return this.getClass().getSimpleName();
    }
}
