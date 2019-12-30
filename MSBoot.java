package main;

import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import main.ClientHandler;
import main.RemoveFinished;

public class MSBoot {
	
	private static final int MAX_PEOPLE = 3;
	
	public static ArrayList<Integer> accountid = new ArrayList<Integer>();
	public static ArrayList<Float> x = new ArrayList<Float>(),
			y = new ArrayList<Float>(),
			xo = new ArrayList<Float>(),
			yo = new ArrayList<Float>();
	public static ArrayList<Integer> max = new ArrayList<Integer>(),
			ind = new ArrayList<Integer>();
	public static ArrayList<String> lead = new ArrayList<String>();
	
	public static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
	public static ExecutorService pool = Executors.newFixedThreadPool(MAX_PEOPLE+1);
	
	private static JFrame frame;
	private static JTextArea ta;
	private static JTextArea tb;
	private static JScrollPane sp;
	private static JScrollPane sb;
	
	private static final int PORT = 9090;
	
	public static void main(String[] args) {
		
		//Get IP
		
        String ip = "error";
        String eip = "error";

        try {
            ip = InetAddress.getLocalHost().toString();
            ip = ip.substring(ip.lastIndexOf("/")+1);
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                            whatismyip.openStream()));

            eip = in.readLine();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
			e.printStackTrace();
		}
        
		//Display Stuff
		
        frame = new JFrame("Field Response Server");
        ta = new JTextArea(10, 20);
        ta.setEditable(false);
        
        sp = new JScrollPane(ta);
        
        tb = new JTextArea(10, 20);
        tb.setEditable(false);
        
        sb = new JScrollPane(tb);
 
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 440);
        frame.getContentPane().add(sp);
        frame.getContentPane().add(sb);
 
        frame.setVisible(true);
        
		//Server Stuff
		pool.execute(new RemoveFinished());
		try {
			ServerSocket ss = new ServerSocket(PORT);
			cappend("[SERVER] Booted Succesfully!\nExternal IP: " + eip + "\nInternal IP: " + ip + "\nLocal Host: 127.0.0.1\nPort: " + PORT);
			try {
				while(true) {
					cappend("[SERVER] Connecting to client...");
					Socket s = ss.accept();
					cappend("[SERVER] Connected to client!");
					ClientHandler clientThread = new ClientHandler(s);
					clients.add(clientThread);
					
					pool.execute(clientThread);
				}
			}
			finally {
				ss.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void cappend(String str) {
		ta.append(str + "\n");
	}
	
	public static void append(String str) {
		tb.append(str + "\n");
	}
	
}
