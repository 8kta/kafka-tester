package is.octavalo.commandLineInterface;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CLI {
    public Scanner scanner;

    public CLI() {
        this.scanner = new Scanner(System.in);
    }

    public static void executeCLI() {
        CLI cli = new CLI();
        cli.buildCLI();
    }

    private void buildCLI() {
        Map<String, Command> commands = this.buildCommands();

        System.out.println("CLI Application Started. Type 'help' for a list of commands, or 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("Goodbye!");
                break;
            } else if (commands.containsKey(input)) {
                commands.get(input).getCommandFunction().run();
            } else {
                System.out.println("Unknown command. Type 'help' for a list of commands.");
            }
        }

        scanner.close();
    }

    private Map<String, Command> buildCommands() {
        Map<String, Command> commands = new HashMap<>();

        commands.put(this.sayHello().getCommandName(), this.sayHello());

        Command help = this.getHelpCommand(commands);
        commands.put(help.getCommandName(), help);

        return commands;

    }

    private Command getHelpCommand(Map<String, Command> commands) {
        Runnable function = () -> {
            for (Command command : commands.values()) {
                System.out.println(command.getHelp());
            }
        };

        return new Command.CommandBuilder("help",
                "Gives the help about a list of commands.")
                .setCommandFunction(function)
                .build();
    }

    private Command sayHello() {
        Runnable function = () -> {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.println("Hello, " + name + "!");
        };

        return new Command.CommandBuilder("sayHello",
                "Prompts for your name and greets you.")
                .setCommandFunction(function).build();
    }


}
