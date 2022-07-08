package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RobotRBullet extends Bullet{
	
   
    
    public RobotRBullet(float x, float y, GameWorldState gameWorld) {
        super(x, y, 60, 30,"robotRbullet" ,1.0f, 10, gameWorld);
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
        //drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void Update() {
            // TODO Auto-generated method stub
        super.Update();
    }

    @Override
    public void attack() {}

}
