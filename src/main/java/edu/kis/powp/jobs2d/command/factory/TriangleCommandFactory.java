package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.impl.ComplexCommand;
import edu.kis.powp.jobs2d.command.impl.OperateToCommand;
import edu.kis.powp.jobs2d.command.impl.SetPositionCommand;

public class TriangleCommandFactory {

    public static ComplexCommand drawTriangle(int width, int height) {
        return new ComplexCommand(
                new SetPositionCommand(0, 0),
                new OperateToCommand(width, 0),
                new OperateToCommand(0, height),
                new OperateToCommand(0, 0)
        );
    }
}
