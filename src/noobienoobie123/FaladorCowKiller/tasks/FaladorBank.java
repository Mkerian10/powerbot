package noobienoobie123.FaladorCowKiller.tasks;

/**
 * Created by larry on 7/17/2017.
 */

import noobienoobie123.FaladorCowKiller.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;


import java.util.concurrent.Callable;

public class FaladorBank extends Task{

    public FaladorBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count()>27 && ctx.bank.nearest().tile().distanceTo(ctx.players.local())<3;
    }

    @Override
    public void execute() {

        if(ctx.bank.opened()){
            if (ctx.bank.depositInventory()){

                Condition.wait(new Callable<Boolean>()
                {
                    @Override
                    public Boolean call() throws Exception
                    {
                        return ctx.inventory.select().count() == 0;
                    }
                }, 200, 20);

            }
        }
        else{
            if (ctx.bank.inViewport()){
                if(ctx.bank.open()){
                    Condition.wait(new Callable<Boolean>()
                    {
                        @Override
                        public Boolean call() throws Exception
                        {
                            return ctx.bank.opened();
                        }
                    }, 250, 20);
                }
                }

            else{
                ctx.camera.turnTo(ctx.bank.nearest());
            }

        }

    }
}
