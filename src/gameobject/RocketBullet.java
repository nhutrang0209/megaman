package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RocketBullet extends Bullet{
	


    private long startTimeForChangeSpeedY;
    
    public RocketBullet(float x, float y, GameWorldState gameWorld) {
        
            super(x, y, 30, 30,"rocket", 1.0f, 10, gameWorld);
            
    }
  
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
            // TODO Auto-generated method stub
            return getBoundForCollisionWithMap();
    }

    @Override
    public void draw(Graphics2D g2) {
            // TODO Auto-generated method stub
        if(getSpeedX() > 0){  
            if(getSpeedY() > 0){
            	animationH.forwardBulletAnimDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            }else if(getSpeedY() < 0){
            	animationH.forwardBulletAnimUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            }else
            	animationH.forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }else{
            if(getSpeedY() > 0){
            	animationH.backBulletAnimDown.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            }else if(getSpeedY() < 0){
            	animationH.backBulletAnimUp.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
            }else
            	animationH.backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }
        //drawBoundForCollisionWithEnemy(g2);
    }

    private void changeSpeedY(){
        if(System.currentTimeMillis() % 3 == 0){
            setSpeedY(getSpeedX());
        }else if(System.currentTimeMillis() % 3 == 1){
            setSpeedY(-getSpeedX());
        }else {
            setSpeedY(0);
            
        }
    }
    
    @Override
    public void Update() {
            // TODO Auto-generated method stub
        super.Update();
        
        if(System.nanoTime() - startTimeForChangeSpeedY > 500*1000000){
            startTimeForChangeSpeedY = System.nanoTime();
            changeSpeedY();
        }
    }

    @Override
    public void attack() {}

}
