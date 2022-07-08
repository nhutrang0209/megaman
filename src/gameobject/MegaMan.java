package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.AnimationHandler;
import effect.CacheDataLoader;
import java.applet.AudioClip;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class MegaMan extends Human {

    public static final int RUNSPEED = 3;
    

    private long lastShootingTime;//thời gian cuối cùng megaman bắn
    private boolean isShooting = false;//trạng thái bắn
    private AudioClip hurtingSound, shooting1;
    
    public MegaMan(float x, float y, GameWorldState gameWorld) {
        super(x, y, 70, 90,"megaMan", 0.1f, 100, gameWorld); 
        shooting1 = CacheDataLoader.getInstance().getSound("bluefireshooting");
        hurtingSound = CacheDataLoader.getInstance().getSound("megamanhurt");
        
        setTeamType(LEAGUE_TEAM);

        setTimeForNoBehurt(2000*1000000);
              
    }

    @Override
    public void Update() {

        super.Update();
        
        if(isShooting){
            if(System.nanoTime() - lastShootingTime > 500*1000000){
                isShooting = false;
            }
        }
        
        if(getIsLanding()){
            animationH.landingBackAnim.Update(System.nanoTime());
            if(animationH.landingBackAnim.isLastFrame()) {
                setIsLanding(false);
                animationH.MegaLanding();
            }
        }
        
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        
        if(getIsDicking()){
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 20;
            rect.width = 44;
            rect.height = 65;
        }else{
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 40;
            rect.width = 44;
            rect.height = 80;
        }
        
        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        
        switch(getState()){
        
            case ALIVE: 
            case NOBEHURT:
                if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
                {
                 //   System.out.println("Plash...");
                }else{
                    
                    if(getIsLanding()){

                        if(getDirection() == RIGHT_DIR){
                        	animationH.landingForwardAnim.setCurrentFrame(animationH.landingBackAnim.getCurrentFrame());
                        	animationH.landingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - animationH.landingForwardAnim.getCurrentImage().getHeight()/2),g2);
                        }else{
                        	animationH.landingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - animationH.landingBackAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }

                    }else if(getIsJumping()){

                        if(getDirection() == RIGHT_DIR){
                        	animationH.flyForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                            	animationH.flyShootingForwardAnim.setCurrentFrame(animationH.flyForwardAnim.getCurrentFrame());
                            	animationH.flyShootingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) + 10, (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                            	animationH.flyForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        }else{
                        	animationH.flyBackAnim.Update(System.nanoTime());
                            if(isShooting){
                            	animationH.flyShootingBackAnim.setCurrentFrame(animationH.flyBackAnim.getCurrentFrame());
                            	animationH.flyShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()) - 10, (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                            	animationH.flyBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                        }

                    }else if(getIsDicking()){

                        if(getDirection() == RIGHT_DIR){
                        	animationH.dickForwardAnim.Update(System.nanoTime());
                        	animationH.dickForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - animationH.dickForwardAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }else{
                        	animationH.dickBackAnim.Update(System.nanoTime());
                        	animationH.dickBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), 
                                    (int) getPosY() - (int) getGameWorld().camera.getPosY() + (getBoundForCollisionWithMap().height/2 - animationH.dickBackAnim.getCurrentImage().getHeight()/2),
                                    g2);
                        }

                    }else{
                        if(getSpeedX() > 0){
                        	animationH.runForwardAnim.Update(System.nanoTime());
                            if(isShooting){
                            	animationH.runShootingForwarAnim.setCurrentFrame(animationH.runForwardAnim.getCurrentFrame() - 1);
                            	animationH.runShootingForwarAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                            	animationH.runForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(animationH.runForwardAnim.getCurrentFrame() == 1) animationH.runForwardAnim.setIgnoreFrame(0);
                        }else if(getSpeedX() < 0){
                        	animationH.runBackAnim.Update(System.nanoTime());
                            if(isShooting){
                            	animationH.runShootingBackAnim.setCurrentFrame(animationH.runBackAnim.getCurrentFrame() - 1);
                            	animationH.runShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            }else
                            	animationH.runBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                            if(animationH.runBackAnim.getCurrentFrame() == 1) animationH.runBackAnim.setIgnoreFrame(0);
                        }else{
                            if(getDirection() == RIGHT_DIR){
                                if(isShooting){
                                	animationH.idleShootingForwardAnim.Update(System.nanoTime());
                                	animationH.idleShootingForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }else{
                                	animationH.idleForwardAnim.Update(System.nanoTime());
                                	animationH.idleForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }
                            }else{
                                if(isShooting){
                                	animationH.idleShootingBackAnim.Update(System.nanoTime());
                                	animationH.idleShootingBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }else{
                                	animationH.idleBackAnim.Update(System.nanoTime());
                                	animationH.idleBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                                }
                            }
                        }            
                    }
                }
                
                break;
            
            case BEHURT:
                if(getDirection() == RIGHT_DIR){
                    animationH.behurtForwardAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }else{
                    animationH.behurtBackAnim.setCurrentFrame(animationH.behurtForwardAnim.getCurrentFrame());
                    animationH.behurtBackAnim.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }
                break;
             
            case FEY:
                
                break;

        }
    }

    @Override
    public void run() {
        if(getDirection() == LEFT_DIR)
            setSpeedX(-3);
        else setSpeedX(3);
    }

    @Override
    public void jump() {

        if(!getIsJumping()){
            setIsJumping(true);
            setSpeedY(-5.0f);           
            animationH.Megaflyreset();
        }
        // for clim wall
        else{
            Rectangle rectRightWall = getBoundForCollisionWithMap();
            rectRightWall.x += 1;
            Rectangle rectLeftWall = getBoundForCollisionWithMap();
            rectLeftWall.x -= 1;
            
            if(getGameWorld().physicalMap.haveCollisionWithRightWall(rectRightWall)!=null && getSpeedX() > 0){
                setSpeedY(-5.0f);
                animationH.Megaflyreset();
            }else if(getGameWorld().physicalMap.haveCollisionWithLeftWall(rectLeftWall)!=null && getSpeedX() < 0){
                setSpeedY(-5.0f);
                animationH.Megaflyreset();
            }
                
        }
    }

    @Override
    public void dick() {
        if(!getIsJumping())
            setIsDicking(true);
    }

    @Override
    public void standUp() {
        setIsDicking(false);
        animationH.MegaStandup();
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        animationH.MegaStoprun();
    }

    @Override
    public void attack() {
    
        if(!isShooting && !getIsDicking()){
            
            shooting1.play();
            
            Bullet bullet = new BlueFire(getPosX(), getPosY(), getGameWorld());
            if(getDirection() == LEFT_DIR) {
                bullet.setSpeedX(-15);
                bullet.setPosX(bullet.getPosX() - 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() - 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }else {
                bullet.setSpeedX(15);
                bullet.setPosX(bullet.getPosX() + 40);
                if(getSpeedX() != 0 && getSpeedY() == 0){
                    bullet.setPosX(bullet.getPosX() + 10);
                    bullet.setPosY(bullet.getPosY() - 5);
                }
            }
            if(getIsJumping())
                bullet.setPosY(bullet.getPosY() - 20);
            
            bullet.setTeamType(getTeamType());
            getGameWorld().bulletManager.addObject(bullet);
            
            lastShootingTime = System.nanoTime();
            isShooting = true;
            
        }
    
    }
    @Override
    public void hurtingCallback(){
        System.out.println("Call back hurting");
        hurtingSound.play();
    }

}
