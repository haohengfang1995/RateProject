package com.wroldline.interview;

public class WidgetMachine {

	private Engine engine;

	public WidgetMachine(Engine engine) {
		this.engine = engine;
	}

    public double produceWidgets(int quantity) {
        engine.start();
        double cost = 0;

        if (engine.isRunning()) {
            cost = produce(quantity);
        }

        engine.stop();

        return cost;
    }

    private double produce(int quantity) {
    	int num = engine.getCost();
        int batch = 0;
        int batchCount = 0;
        double costPerBatch = engine.getCostPerBatch();
        
        while(batch < quantity) {
        	batch = batch + num;
        	batchCount++;
        }
        
        return batchCount * costPerBatch;
    }
}
