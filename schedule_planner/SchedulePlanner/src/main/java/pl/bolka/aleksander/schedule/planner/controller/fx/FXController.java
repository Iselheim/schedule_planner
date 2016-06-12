package pl.bolka.aleksander.schedule.planner.controller.fx;

import pl.bolka.aleksander.schedule.planner.config.ScreensConfig;

public abstract class FXController {

	protected ScreensConfig flow;

	public FXController(ScreensConfig flow) {
		this.flow = flow;
	}

	public abstract String getPath();
}
