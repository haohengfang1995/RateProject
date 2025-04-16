package com.wroldline.interview;

import com.wroldline.interview.FuelType.FuelCategory;

public class InternalCombustionEngine extends Engine{

	  public InternalCombustionEngine(FuelType requiredFuelType) {
	       super(requiredFuelType);
	    }

	    @Override
	    public void start() {
	        if (getFuelLevel() > 0 && getRequiredFuelType().equals(getFuelType()) && getRequiredFuelType().getFuelType().equals(FuelCategory.OIL)) {
	            open();
	        } else {
	            throw new IllegalStateException("Not able to start engine.");
	        }
	    }
	    
}
