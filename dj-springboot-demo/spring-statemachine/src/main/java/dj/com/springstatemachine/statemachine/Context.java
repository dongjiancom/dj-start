
package dj.com.springstatemachine.statemachine;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author pony 2022/11/28 create
 */
@Data
@AllArgsConstructor
public class Context<T> {

    T data;

}
