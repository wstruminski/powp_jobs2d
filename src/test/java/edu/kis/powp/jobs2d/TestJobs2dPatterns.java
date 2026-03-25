package edu.kis.powp.jobs2d;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.kis.legacy.drawer.panel.DefaultDrawerFrame;
import edu.kis.legacy.drawer.panel.DrawPanelController;
import edu.kis.legacy.drawer.shape.LineFactory;
import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.command.DriverCommand;
import edu.kis.powp.jobs2d.command.factory.CircleCommandFactory;
import edu.kis.powp.jobs2d.command.factory.JoeCommandFactory;
import edu.kis.powp.jobs2d.command.factory.RectangleCommandFactory;
import edu.kis.powp.jobs2d.command.factory.TriangleCommandFactory;
import edu.kis.powp.jobs2d.drivers.adapter.LineDrawerAdapter;
import edu.kis.powp.jobs2d.events.SelectChangeVisibleOptionListener;
import edu.kis.powp.jobs2d.events.SelectTestFigureOptionListener;
import edu.kis.powp.jobs2d.features.DrawerFeature;
import edu.kis.powp.jobs2d.features.DriverFeature;

public class TestJobs2dPatterns {
	private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	private static class CommandTest {
		private final String name;
		private final DriverCommand command;

		public CommandTest(String name, DriverCommand command) {
			this.name = name;
			this.command = command;
		}
	}

	/**
	 * Setup test concerning preset figures in context.
	 *
	 * @param application Application context.
	 */
	private static void setupPresetTests(Application application) {
		SelectTestFigureOptionListener selectTestFigureOptionListener = new SelectTestFigureOptionListener(
				DriverFeature.getDriverManager(),
				null
		);

		List<CommandTest> tests = Arrays.asList(
				new CommandTest("Figure Joe 1", JoeCommandFactory.figuresJoe1()),
				new CommandTest("Figure Joe 2", JoeCommandFactory.figuresJoe2()),
				new CommandTest("Double Joe", JoeCommandFactory.doubleJoe()),
				new CommandTest("Triangle", TriangleCommandFactory.drawTriangle(150, 200)),
				new CommandTest("Rectangle", RectangleCommandFactory.drawRectangle(100, 50)),
				new CommandTest("Circle", CircleCommandFactory.drawCircle(60, 100))
		);
		for (CommandTest test : tests) {
			application.addTest(test.name, selectTestFigureOptionListener.withScript(test.command::execute));
		}
	}

	/**
	 * Setup driver manager, and set default driver for application.
	 *
	 * @param application Application context.
	 */
	private static void setupDrivers(Application application) {
		Job2dDriver loggerDriver = new LoggerDriver();
		DriverFeature.addDriver("Logger Driver", loggerDriver);
		DriverFeature.getDriverManager().setCurrentDriver(loggerDriver);

		LineDrawerAdapter testDriver = new LineDrawerAdapter(DrawerFeature.getDrawerController());
		DriverFeature.addDriver("Buggy Simulator", testDriver);
		DriverFeature.addDriver("Dotted Buggy Simulator", testDriver.withSupplier(LineFactory::getDottedLine));
		DriverFeature.addDriver("Special Buggy Simulator", testDriver.withSupplier(LineFactory::getSpecialLine));

		DriverFeature.updateDriverInfo();
	}

	/**
	 * Auxiliary routines to enable using Buggy Simulator.
	 *
	 * @param application Application context.
	 */
	private static void setupDefaultDrawerVisibilityManagement(Application application) {
		DefaultDrawerFrame defaultDrawerWindow = DefaultDrawerFrame.getDefaultDrawerFrame();
		application.addComponentMenuElementWithCheckBox(DrawPanelController.class, "Default Drawer Visibility",
				new SelectChangeVisibleOptionListener(defaultDrawerWindow), true);
		defaultDrawerWindow.setVisible(true);
	}

	/**
	 * Setup menu for adjusting logging settings.
	 *
	 * @param application Application context.
	 */
	private static void setupLogger(Application application) {
		application.addComponentMenu(Logger.class, "Logger", 0);
		application.addComponentMenuElement(Logger.class, "Clear log",
				(ActionEvent e) -> application.flushLoggerOutput());
		application.addComponentMenuElement(Logger.class, "Fine level", (ActionEvent e) -> logger.setLevel(Level.FINE));
		application.addComponentMenuElement(Logger.class, "Info level", (ActionEvent e) -> logger.setLevel(Level.INFO));
		application.addComponentMenuElement(Logger.class, "Warning level",
				(ActionEvent e) -> logger.setLevel(Level.WARNING));
		application.addComponentMenuElement(Logger.class, "Severe level",
				(ActionEvent e) -> logger.setLevel(Level.SEVERE));
		application.addComponentMenuElement(Logger.class, "OFF logging", (ActionEvent e) -> logger.setLevel(Level.OFF));
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Application app = new Application("2d jobs Visio");
				DrawerFeature.setupDrawerPlugin(app);

				DriverFeature.setupDriverPlugin(app);
				setupDrivers(app);
				setupPresetTests(app);
				setupLogger(app);

				app.setVisibility(true);
			}
		});
	}

}
