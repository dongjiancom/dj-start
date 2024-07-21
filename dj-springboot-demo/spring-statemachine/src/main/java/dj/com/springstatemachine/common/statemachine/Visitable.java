
package dj.com.springstatemachine.common.statemachine;

public interface Visitable {
    String accept(final Visitor visitor);
}
