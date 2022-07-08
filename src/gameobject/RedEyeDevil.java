/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.AnimationHandler;
import effect.CacheDataLoader;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class RedEyeDevil extends ParticularObject {


    private long startTimeToShoot;
    
    private AudioClip shooting;


    
    public RedEyeDevil(float x, float y, GameWorldState gameWorld) {
        super(x, y, 127, 89,"redeye", 0, 100, gameWorld);
        startTimeToShoot = 0;
        setDamage(10);//chạm mất 10 máu
        setTimeForNoBehurt(300000000);
        shooting = CacheDataLoader.getInstance().getSound("redeyeshooting");
    }

    @Override
    public void attack() {
    
        shooting.play();
        Bullet bullet = new RedEyeBullet(getPosX(), getPosY(), getGameWorld());
        if(getDirection() == LEFT_DIR) bullet.setSpeedX(-8);//set vận tốc đạn
        else bullet.setSpeedX(8);
        bullet.setTeamType(getTeamType());
        getGameWorld().bulletManager.addObject(bullet);
    
    }

    
    public void Update(){
        super.Update();
        if(System.nanoTime() - startTimeToShoot > 1000*10000000){//1s bắn 1 lần
            attack();
            System.out.println("Red Eye attack");
            startTimeToShoot = System.nanoTime();//set lại thời gian nó vừa bắn
        }
    }
    
    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if(!isObjectOutOfCameraView()){
            if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1){
                // plash...
            }else{
                if(getDirection() == LEFT_DIR){
                    animationH.backAnim.Update(System.nanoTime());
                    animationH.backAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }else{
                    animationH.forwardAnim.Update(System.nanoTime());
                    animationH.forwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                            (int)(getPosY() - getGameWorld().camera.getPosY()), g2);
                }
            }
        }
    }
    
}
