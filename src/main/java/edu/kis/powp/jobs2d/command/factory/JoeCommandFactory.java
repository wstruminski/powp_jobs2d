package edu.kis.powp.jobs2d.command.factory;

import edu.kis.powp.jobs2d.command.impl.ComplexCommand;

public class JoeCommandFactory {

    public static ComplexCommand figuresJoe1() {
        return new ComplexCommand.Builder(-120, -120)
                .setPosition(-120, -120)
                .operateTo(120, -120)
                .operateTo(120, 120)
                .operateTo(-120, 120)
                .operateTo(-120, -120)
                .operateTo(120, 120)
                .setPosition(120, -120)
                .operateTo(-120, 120)
                .build();
    }

    public static ComplexCommand figuresJoe2() {
        return new ComplexCommand.Builder()
                .operateTo(233, -23)
                .operateTo(24, 90)
                .operateTo(-104, 80)
                .operateTo(11, -22)
                .operateTo(-33, 44)
                .operateTo(-66, -88)
                .operateTo(128, 256)
                .build();
    }

    public static ComplexCommand doubleJoe() {
        return new ComplexCommand.Builder()
                .chain(figuresJoe1())
                .chain(figuresJoe2())
                .build();
    }
}
