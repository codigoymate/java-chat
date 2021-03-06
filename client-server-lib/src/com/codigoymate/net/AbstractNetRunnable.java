package com.codigoymate.net;

public abstract class AbstractNetRunnable implements NetRunnable {
	protected boolean running = false;
	
	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void run() {
		start();
		
		running = true;
		
		while (running) {
			process();
		}
		
		running = false;
	}

	@Override
	public void stop() {
		running = false;
	}
}
