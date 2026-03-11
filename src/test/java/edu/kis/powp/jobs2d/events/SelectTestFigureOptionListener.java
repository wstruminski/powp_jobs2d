package edu.kis.powp.jobs2d.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.drivers.DriverManager;

public class SelectTestFigureOptionListener implements ActionListener {

	private final DriverManager driverManager;
	private final Consumer<Job2dDriver> testScript;

	public SelectTestFigureOptionListener(DriverManager driverManager, Consumer<Job2dDriver> testScript) {
		this.driverManager = driverManager;
		this.testScript = testScript;
	}

	public SelectTestFigureOptionListener withScript(Consumer<Job2dDriver> newScript) {
		return new SelectTestFigureOptionListener(driverManager, newScript);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		testScript.accept(driverManager.getCurrentDriver());
	}
}
