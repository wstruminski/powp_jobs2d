package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.impl.ComplexCommand;
import edu.kis.powp.jobs2d.command.impl.OperateToCommand;
import edu.kis.powp.jobs2d.command.impl.SetPositionCommand;

import java.util.ArrayList;

public class CircleCommandFactory {

    public static ComplexCommand drawCircle(int radius, int points) {
        ArrayList<DriverCommand> commands = new ArrayList<>();
        commands.add(new SetPositionCommand(0, radius));
        points = Math.max(3, points);

        for (int i = 0; i < points; i++) {
            double sin = Math.sin(i * 2 * Math.PI / points);
            double cos = Math.cos(i * 2 * Math.PI / points);

            commands.add(new OperateToCommand((int) (radius * sin), (int) (radius * cos)));
        }

        return new ComplexCommand(commands);
    }
}
