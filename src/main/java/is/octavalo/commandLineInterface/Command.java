package is.octavalo.commandLineInterface;

import java.util.Objects;

public class Command {

    private final String commandName;
    private final String commandDescription;
    private final Runnable commandFunction;

    public Command(String commandName, String commandDescription, Runnable commandFunction) {
        this.commandName = commandName;
        this.commandDescription = commandDescription;
        this.commandFunction = commandFunction;
    }

    public Command(CommandBuilder builder) {
        this.commandName = builder.commandName;
        this.commandDescription = builder.commandDescription;
        this.commandFunction = builder.commandFunction;
    }

    public String getCommandName() {
        return this.commandName;
    }

    public String getCommandDescription() {
        return this.commandDescription;
    }

    public Runnable getCommandFunction() {
        return this.commandFunction;
    }

    public String getHelp() {
        return this.commandName + " - " + this.commandDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return Objects.equals(commandName, command.commandName) && Objects.equals(commandDescription, command.commandDescription) && Objects.equals(commandFunction, command.commandFunction);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandName, commandDescription, commandFunction);
    }

    public static class CommandBuilder {
        private final String commandName;
        private final String commandDescription;

        private Runnable commandFunction;

        public CommandBuilder(String commandName, String commandDescription) {
            this.commandName = commandName;
            this.commandDescription = commandDescription;
        }

        public CommandBuilder setCommandFunction(Runnable commandFunction) {
            this.commandFunction = commandFunction;
            return this;
        }

        public Command build() {
            return new Command(this);
        }

    }

}
