package helpers;

import java.io.*;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;

public class Artist {

	public static int Width, Height, TileWidth, TileHeight, l = 3;
	public static boolean hideUI = false,hideChatUI = false,debug = false;
	public static boolean canSend = true;
	
	public static void BeginSession(String name) {
		Width = 1280;
		Height = 960;
		TileWidth = Width / 20;
		TileHeight = Height / 15;
		
		Display.setTitle(name);
		try {
			Display.setDisplayMode(new DisplayMode(Width, Height));
			Display.create();
		} catch (LWJGLException e) {
			
			e.printStackTrace();
		}
		
		InitGL();
	}
	
	public static void InitGL() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Width, Height, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	public static void UpdateDisplay() {
		Display.update();
		if(l==1) Display.sync(15);
		if(l==2) Display.sync(30);
		if(l==3) Display.sync(60);
		if(l==4) Display.sync(120);
		if(Width != Display.getWidth()) {
			Width = Display.getWidth();
			TileWidth = Width / 20;
		}
		if(Height != Display.getHeight()) {
			Height = Display.getHeight();
			TileHeight = Height / 15;
		}
	}
	
	public static boolean CheckCollision(float x1, float y1, float width1, float height1,
			float x2, float y2, float width2, float height2) {
		if(x1 + width1 > x2 && x1 < x2 + width2 &&
				y1 + height1 > y2 && y1 < y2 + height2) {
			return true;
		}
		return false;
	}
	
	public static void DrawQuad(float x, float y, float width, float height) {
		glBegin(GL_QUADS);
		glVertex2f(x,y); //Top left corner
		glVertex2f(x + width, y); //Top right corner
		glVertex2f(x + width, y + height); //Bottom right corner
		glVertex2f(x, y + height); //Bottom left corner
		glEnd();
	}
	
	public static void DrawQuadTex(Texture tex, float x, float y, float width, float height) {
		tex.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static void DrawQuadTexRot(Texture tex, float x, float y, float width, float height, float angle) {
		tex.bind();
		glTranslatef(x + width / 2, y + height / 2, 0);
		glRotatef(angle, 0, 0, 1);
		glTranslatef(- width / 2, - height / 2, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			System.err.println("Texture Failed: " + path);
			e.printStackTrace();
		}
		return tex;
	}
	
	public static Texture QuickLoad(String name) {
		return LoadTexture("res/" + name + ".png", "PNG");
	}
	
	public static void text(String text) {
		System.out.println(text);
	}
}
