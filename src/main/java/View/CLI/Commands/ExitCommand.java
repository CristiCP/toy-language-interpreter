package View.CLI.Commands;

public class ExitCommand extends Command {
    public ExitCommand() {
        super("0", "Exit");
    }

    public void execute() {
        System.out.println("Quitting!");
        System.exit(0);
    }
}
