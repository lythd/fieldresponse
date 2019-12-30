package main;

import java.util.concurrent.ThreadPoolExecutor;

public class RemoveFinished implements Runnable {
	
	@Override
	public void run() {
		for(int i = 0; i < MSBoot.clients.size(); i++) {
			ClientHandler c = MSBoot.clients.get(i);
			if (c.finished) {
				((ThreadPoolExecutor) MSBoot.pool).remove(c);
				MSBoot.clients.remove(c);
			}
		}
	}

}
