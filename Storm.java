public class Storm implements Command{

    @Override
    public String execute(Game game) {
        String message = "There was a storm last night. Luckily, you evacuated the villagers. You lost ";
        int health = RandomEventHelper.losingHealth(game, 10);
        message = message + health + " health. ";
        game.setHealth(health);
        return message;

    }
}
