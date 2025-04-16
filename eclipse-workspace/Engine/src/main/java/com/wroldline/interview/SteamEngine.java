package com.wroldline.interview;

import com.wroldline.interview.FuelType.FuelCategory;

public class SteamEngine extends Engine{

	public SteamEngine(FuelType requiredFuelType) {
		super(requiredFuelType);
	}

	@Override
	public void start() {
		if (getFuelLevel() > 0 && getRequiredFuelType().equals(getFuelType()) && getRequiredFuelType().getFuelType().equals(FuelCategory.STEAM)) {
			open();
		} else {
			throw new IllegalStateException("Not able to start engine.");
		}
	}
}
