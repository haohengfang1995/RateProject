package com.wroldline.interview;

import com.wroldline.interview.FuelType.FuelCategory;


//創造一個抽象父類別
public abstract class Engine {

	private boolean running;
	private int fuelLevel;
	private FuelType requiredFuelType;
	private FuelType fuelType;
	private int cost;//新增批次數量
	private double costPerBatch;//新增成本數量
	
	public Engine(FuelType requiredFuelType) {
		this.requiredFuelType = requiredFuelType;
		running = false;
		fuelLevel = 0;
	}
	
	public void open() {
		this.running = true;
	}
	
	public void stop() {
		this.running = false;
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public void fill(FuelType fuelType, int fuelLevel) {
      if (fuelLevel >= 0 && fuelLevel <= 100) {
          this.fuelLevel = fuelLevel;
      }
      else if (fuelLevel > 100) {
          this.fuelLevel = 100;
      }
      else {
          this.fuelLevel = 0;
      }

      this.fuelType = fuelType;
  }
	
	public FuelType getFuelType() {
		return fuelType;
	}
	
	public FuelType getRequiredFuelType() {
		return requiredFuelType;
	}
	
	public int getFuelLevel() {
		return fuelLevel;
	}
	
	public int getCost() {
		return requiredFuelType.getFuelType() == FuelCategory.OIL ? 8 : 2; 
	}
	
	public double getCostPerBatch() {
		return requiredFuelType.getCostPerBatch();
	}
	
	//留一個抽象啟動方法
	public void start() {};
	
	
	
}
