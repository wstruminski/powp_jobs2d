package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.impl.ComplexCommand;
import edu.kis.powp.jobs2d.command.impl.OperateToCommand;
import edu.kis.powp.jobs2d.command.impl.SetPositionCommand;

public class RectangleCommandFactory {

    public static ComplexCommand drawRectangle(int width, int height) {
        return new ComplexCommand(
                new SetPositionCommand(0, 0),
                new OperateToCommand(width, 0),
                new OperateToCommand(width, height),
                new OperateToCommand(0, height),
                new OperateToCommand(0, 0)
        );
    }
}
