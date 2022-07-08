package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.AnimationHandler;
import effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class BlueFire extends Bullet{
	
   
    public BlueFire(float x, float y, GameWorldState gameWorld) {
        super(x, y, 60, 30,"bluefire", 1.0f, 10, gameWorld);// tọa độ lúc bắn ra
        
    }
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() { // va chạm với enemy
        return getBoundForCollisionWithMap();// giống thông số với map
    }

    @Override
    public void draw(Graphics2D g2) {
        if(getSpeedX() > 0){
            if(animationH.BulletFMoving()){
            	animationH.BulletFIgnore012();
            }
                
            animationH.forwardBulletAnim.Update(System.nanoTime());
            animationH.forwardBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }else{
            if(animationH.BulletBMoving()){
            	animationH.BulletBIgnore012();
            }
            animationH.backBulletAnim.Update(System.nanoTime());
            animationH.backBulletAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
        }
    }

    @Override
    public void Update() {
        if(animationH.forwardBulletAnim.isIgnoreFrame(0) || animationH.backBulletAnim.isIgnoreFrame(0))//nếu ignore thì di chuyển -> cập nhật vị trí
            setPosX(getPosX() + getSpeedX());
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
        if(object!=null && object.getState() == ALIVE){
            setBlood(0);
            object.setBlood(object.getBlood() - getDamage());
            object.setState(BEHURT);
            System.out.println("Bullet set behurt for enemy");
        }
    }

    @Override
    public void attack() {}// gây ra sát thương bằng chạy

}
