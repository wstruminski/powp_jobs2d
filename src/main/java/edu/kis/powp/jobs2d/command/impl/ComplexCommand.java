package edu.kis.powp.jobs2d.command.impl;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.command.DriverCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplexCommand implements DriverCommand {

    private final DriverCommand[] commands;

    public ComplexCommand(DriverCommand... commands) {
        this.commands = commands;
    }

    public ComplexCommand(List<DriverCommand> commandList) {
        this.commands = commandList.toArray(new DriverCommand[0]);
    }

    @Override
    public void execute(Job2dDriver driver) {
        for (DriverCommand command : this.commands) {
            command.execute(driver);
        }
    }

    public static class Builder {
        private final List<DriverCommand> commands = new ArrayList<>();

        public Builder() {
            this(0, 0);
        }

        public Builder(int initialX, int initialY) {
            this.commands.add(new SetPositionCommand(initialX, initialY));
        }

        public Builder chain(ComplexCommand command) {
            this.commands.addAll(Arrays.asList(command.commands));
            return this;
        }

        public Builder setPosition(int x, int y) {
            this.commands.add(new SetPositionCommand(x, y));
            return this;
        }

        public Builder operateTo(int x, int y) {
            this.commands.add(new OperateToCommand(x, y));
            return this;
        }

        public ComplexCommand build() {
            return new ComplexCommand(commands);
        }
    }
}
