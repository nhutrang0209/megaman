
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import state.GameWorldState;
import effect.CacheDataLoader;
import userinterface.GameFrame;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author phamn
 */
public class BackgroundMap extends GameObject {//hiển thị cảnh vật trong game

    public int[][] map;
    private int tileSize;
    
    public BackgroundMap(float x, float y, GameWorldState gameWorld) {
        super(x, y, gameWorld);
        map = CacheDataLoader.getInstance().getBackgroundMap();
        tileSize = 30;
    }

    @Override
    public void Update() {}// Các đối tượng không cần update
    
    public void draw(Graphics2D g2){
        
        Camera camera = getGameWorld().camera;// Get camera để cập nhật vị trí để vẽ background
        
        g2.setColor(Color.RED); // Không cần thiết
        // Chỉ vẽ background trong tầm của camera
        for(int i = 0;i< map.length;i++)
            for(int j = 0;j<map[0].length;j++)
                if(map[i][j]!=0 && j*tileSize - camera.getPosX() > -30 && j*tileSize - camera.getPosX() < GameFrame.SCREEN_WIDTH
                        && i*tileSize - camera.getPosY() > -30 && i*tileSize - camera.getPosY() < GameFrame.SCREEN_HEIGHT){  // Xét tọa độ có nằm trong vùng của camera không thì vẽ
                    g2.drawImage(CacheDataLoader.getInstance().getFrameImage("tiled"+map[i][j]).getImage(), (int) getPosX() + j*tileSize - (int) camera.getPosX(), 
                        (int) getPosY() + i*tileSize - (int) camera.getPosY(), null);
                }
        
    }
    
}
