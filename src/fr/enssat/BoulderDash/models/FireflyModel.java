package fr.enssat.BoulderDash.models;

public class FireflyModel extends MovableBlockModel {
	private static String spriteName;
	private static int priority;

	static {
		spriteName = "field_00";
		priority = 2;
	}

	public FireflyModel(int x, int y) {
		super(spriteName, priority, x, y);
	}
}
