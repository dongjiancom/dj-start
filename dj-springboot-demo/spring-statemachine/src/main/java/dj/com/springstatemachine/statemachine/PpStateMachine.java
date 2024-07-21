package dj.com.springstatemachine.statemachine;

import dj.com.springstatemachine.common.statemachine.StateMachine;
import dj.com.springstatemachine.common.statemachine.builder.StateMachineBuilder;
import dj.com.springstatemachine.common.statemachine.builder.StateMachineBuilderFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;


/**
 * @author pony 2022/11/28 create
 */
@Component
public class PpStateMachine implements InitializingBean {
    private StateMachine<Status.TradeStatus, Events.TradeEvents, Context> stateMachine;

    @Override
    public void afterPropertiesSet() throws Exception {
        StateMachineBuilder<Status.TradeStatus, Events.TradeEvents, Context> builder = StateMachineBuilderFactory.create();
        //external transition
        builder.externalTransition()
                .from(Status.TradeStatus.CREATED)
                .to(Status.TradeStatus.PAYING)
                .on(Events.TradeEvents.UP_STREAM_RETURN_PAYING);

        builder.externalTransition()
                .from(Status.TradeStatus.CREATED)
                .to(Status.TradeStatus.SUCCESS)
                .on(Events.TradeEvents.UP_STREAM_RETURN_SUCCESS);

        builder.externalTransition()
                .from(Status.TradeStatus.PAYING)
                .to(Status.TradeStatus.SUCCESS)
                .on(Events.TradeEvents.UP_STREAM_RETURN_SUCCESS);

        builder.externalTransition()
                .from(Status.TradeStatus.CREATED)
                .to(Status.TradeStatus.FAIL)
                .on(Events.TradeEvents.UP_STREAM_RETURN_FAIL);

        builder.externalTransition()
                .from(Status.TradeStatus.PAYING)
                .to(Status.TradeStatus.FAIL)
                .on(Events.TradeEvents.UP_STREAM_RETURN_FAIL);

        builder.externalTransition()
                .from(Status.TradeStatus.CREATED)
                .to(Status.TradeStatus.FAIL)
                .on(Events.TradeEvents.UP_STREAM_REQ_FAIL);

        builder.externalTransition()
                .from(Status.TradeStatus.CREATED)
                .to(Status.TradeStatus.PAYING)
                .on(Events.TradeEvents.UP_STREAM_REQ_TIMEOUT);

        stateMachine = builder.build("pp-stateMachine");
    }

    public Status.TradeStatus fireEvent(Status.TradeStatus ori, Events.TradeEvents event) {
        if (Events.TradeEvents.UP_STREAM_IGNORE == event) {
            return ori;
        }
        return stateMachine.fireEvent(ori, event, new Context(5L));
    }

    public Status.TradeStatus fireEvent(Status.TradeStatus ori, Events.TradeEvents event, Context ctx) {
        return stateMachine.fireEvent(ori, event, ctx);
    }

}
