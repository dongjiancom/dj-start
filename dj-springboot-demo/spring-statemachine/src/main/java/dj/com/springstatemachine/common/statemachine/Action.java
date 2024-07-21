
package dj.com.springstatemachine.common.statemachine;

public interface Action<S, E, C> {
    public void execute(S from, S to, E event, C context);
}
