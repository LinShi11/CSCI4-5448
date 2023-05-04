package CommandRandomEvents;

import GamePlay.*;

/**
 * The random event class contains all the possible random events
 */
public class RandomEvents {
    Invoker invoker = new Invoker();
    private Game game;
    public RandomEvents(Game game){
        this.game = game;
    }
    /**
     * command pattern: robbed, loses food and gold
     * @return: the message for gameUI
     */
    public String robbed(){
        invoker.setCommand(new Robbed());
        return invoker.execute(game);
    }

    /**
     * command pattern: monsters attack, loses resource, villager, and health
     * @return: the message for gameUI
     */
    public String monsterAttack(){
        invoker.setCommand(new MonsterAttack());
        return invoker.execute(game);
    }

    /**
     * command pattern: villagers leaving. Only occurs when the villagers are unhappy
     * @return: the message for gameUI
     */
    public String peopleLeaving(boolean unhappy){
        if(unhappy) {
            invoker.setCommand(new PeopleLeaving());
            return invoker.execute(game);
        }else{
            return "No event has occurred";
        }
    }

    /**
     * command pattern: small storm. Loses health
     * @return: the message for gameUI
     */
    public String smallStorm(){
        invoker.setCommand(new Storm());
        return invoker.execute(game);
    }
}
