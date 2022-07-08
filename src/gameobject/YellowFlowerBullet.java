package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class YellowFlowerBullet extends Bullet{
	
    
    public YellowFlowerBullet(float x, float y, GameWorldState gameWorld) {
            super(x, y, 30, 30,"yellow_flower_bullet", 1.0f, 10, gameWorld);
    }

    
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
        if(getSpeedX() > 0){          
        	animationH.forwardBulletAnim.Update(System.nanoTime());
        	animationH.forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }else{
        	animationH.backBulletAnim.Update(System.nanoTime());
        	animationH.backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }
    }

    @Override
    public void Update() {
        super.Update();
    }

    @Override
    public void attack() {}

}
