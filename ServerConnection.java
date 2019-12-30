package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

import entities.OtherPlayer;

public class ServerConnection implements Runnable {

	private Socket s;
	private BufferedReader reciever;
	private InputStream in;
	private MultiPlayer mp;
	
	public ServerConnection (Socket s_, MultiPlayer mp_) throws IOException {
		mp = mp_;
		s = s_;
		
		in = s.getInputStream();
		reciever = new BufferedReader(new InputStreamReader(in));
	}
	
	@Override
	public void run() {
		try {
			while (true) {
				String serverResponse = reciever.readLine();
				if(serverResponse == null) break;
				String[] broken = serverResponse.split(" ");
				if(broken[0].equals("got")) {
					mp.players = new ArrayList<OtherPlayer>();
					for(int i = 0; i * 8 < broken.length - 1; i++) {
						int i1 = Integer.parseInt(broken[i+1]);
						if(i1!=mp.ci) {
							float i2 = Float.parseFloat(broken[i+2]);
							float i3 = Float.parseFloat(broken[i+3]);
							float i4 = Float.parseFloat(broken[i+4]);
							float i5 = Float.parseFloat(broken[i+5]);
							int i6 = Integer.parseInt(broken[i+6]);
							int i7 = Integer.parseInt(broken[i+7]);
							String i8 = broken[i+8];
							mp.players.add(new OtherPlayer(i1,i2,i3,i4,i5,i6,i7,i8));
						}
					}
				} else if(broken[0].equals("upd")) {
					int i1 = Integer.parseInt(broken[1]);
					if(i1!=mp.ci) {
						float i2 = Float.parseFloat(broken[2]);
						float i3 = Float.parseFloat(broken[3]);
						float i4 = Float.parseFloat(broken[4]);
						float i5 = Float.parseFloat(broken[5]);
						int i6 = Integer.parseInt(broken[6]);
						int i7 = Integer.parseInt(broken[7]);
						String i8 = broken[8];
						boolean found = false;
						for(OtherPlayer p : mp.players) {
							if(p.id() == i1) {
								found = true;
								p.recieve(i2,i3,i4,i5,i6,i7,i8);
							}
						}
						if(!found) {
							mp.players.add(new OtherPlayer(i1,i2,i3,i4,i5,i6,i7,i8));
						}
					}
				} else if(broken[0].equals("say")) {
					StateManager.addChat(serverResponse.substring(4));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
