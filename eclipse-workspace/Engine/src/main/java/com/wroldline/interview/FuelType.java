package com.wroldline.interview;

public enum FuelType {
    PETROL(FuelCategory.OIL, 9),
    DIESEL(FuelCategory.OIL, 12),
    WOOD(FuelCategory.STEAM, 4.35d),
    COAL(FuelCategory.STEAM, 5.65d);
    
    private final FuelCategory fuelCategory;
    private final double costPerBatch;
	
	FuelType(FuelCategory fuelCategory, double costPerBatch) {
		this.fuelCategory = fuelCategory;
		this.costPerBatch = costPerBatch;
	}
	
	public FuelCategory getFuelType() {
		return fuelCategory;
	}
	
	public double getCostPerBatch() {
		return costPerBatch;
	}
	
	enum FuelCategory{
		OIL,STEAM
	}
}
