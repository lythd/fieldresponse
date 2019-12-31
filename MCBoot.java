package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

	private Socket s;
	private BufferedReader reciever;
	private InputStream in;
	private PrintWriter sender;
	public boolean finished = false;
	
	public ClientHandler(Socket s_) throws IOException {
		s = s_;
		
		sender = new PrintWriter(s.getOutputStream(),true);
		in = s.getInputStream();
		reciever = new BufferedReader(new InputStreamReader(in));
	}
	
	@Override
	public void run() {
		if(finished) return;
		String request = "";
		while (true) {
			
			try {
				request = reciever.readLine();
			} catch (IOException e) {
				break;
			}
			if(request.equals("stp")) {
				MSBoot.append("[THREAD] Close request recieved...");
				break;
			}
			if(request.equals("get")) {
				MSBoot.append("[THREAD] Preparing data...");
				String send = "got ";
				for(int i = 0; i < MSBoot.accountid.size(); i++) {
					MSBoot.append("[THREAD] Preparing player data...");
					send += MSBoot.accountid.get(i) + " ";
					send += MSBoot.x.get(i) + " ";
					send += MSBoot.y.get(i) + " ";
					send += MSBoot.xo.get(i) + " ";
					send += MSBoot.yo.get(i) + " ";
					send += MSBoot.max.get(i) + " ";
					send += MSBoot.ind.get(i) + " ";
					send += MSBoot.lead.get(i) + " ";
				}
				MSBoot.append("[THREAD] Sending data...");
				sender.println(send);
				MSBoot.append("[THREAD] Sent data!");
			} else if(request.startsWith("upd")) {
				MSBoot.append("[THREAD] Internalising the update...");
				String[] broken = request.split(" ");
				int i1 = Integer.parseInt(broken[1]);
				float i2 = Float.parseFloat(broken[2]);
				float i3 = Float.parseFloat(broken[3]);
				float i4 = Float.parseFloat(broken[4]);
				float i5 = Float.parseFloat(broken[5]);
				int i6 = Integer.parseInt(broken[6]);
				int i7 = Integer.parseInt(broken[7]);
				String i8 = broken[8];
				boolean found = false;
				for (int i = 0; i < MSBoot.accountid.size(); i++) {
					if(MSBoot.accountid.get(i).equals(i1)) {
						found = true;
						MSBoot.x.set(i,i2);
						MSBoot.y.set(i,i3);
						MSBoot.xo.set(i,i4);
						MSBoot.yo.set(i,i5);
						MSBoot.max.set(i,i6);
						MSBoot.ind.set(i,i7);
						MSBoot.lead.set(i,i8);
						MSBoot.append("[THREAD] Index Updated!");
					}
				}
				if(!found) {
					MSBoot.accountid.add(i1);
					MSBoot.x.add(i2);
					MSBoot.y.add(i3);
					MSBoot.xo.add(i4);
					MSBoot.yo.add(i5);
					MSBoot.max.add(i6);
					MSBoot.ind.add(i7);
					MSBoot.lead.add(i8);
					MSBoot.append("[THREAD] Index Made!");
				}
				MSBoot.append("[THREAD] Distributing update...");
				outToAll(request);
				MSBoot.append("[THREAD] Distributed update!");
			} else if (request.startsWith("say")) {
				MSBoot.append("[THREAD] Distributing chat...");
				outToAll(request);
				MSBoot.append("[THREAD] Distributed chat!");
			} else {
				MSBoot.append("[THREAD] Unknown command: \"" + request + "\"");
			}
			
		}

		finished = true;
		MSBoot.append("[THREAD] Closing client thread...");
		
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void outToAll(String msg) {
		for(ClientHandler c : MSBoot.clients) {
			c.sender.println(msg);
		}
	}

}
