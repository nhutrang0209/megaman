package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.AnimationHandler;
import effect.CacheDataLoader;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Hashtable;

//kế thừa lớp cha Human
public class FinalBoss extends Human {

    
    private long startTimeForAttacked;
    
    private Hashtable<String, Long> timeAttack = new Hashtable<String, Long>(); //<hành động, thời gian của hành động>
    private String[] attackType = new String[4];//mảng các hành động
    private int attackIndex = 0;//tăng theo mảng attackType
    private long lastAttackTime;//hoạt động trước kết thúc lúc nào, để tính thời gian cho hoạt động tiếp theo 
    

    public FinalBoss(float x, float y, GameWorldState gameWorld) {
        super(x, y, 110, 150,"finalBoss", 0.1f, 100, gameWorld);
        setTimeForNoBehurt(500*1000000);
        setDamage(10);
        //khởi tạo mảng
        attackType[0] = "NONE";
        attackType[1] = "shooting";
        attackType[2] = "NONE";
        attackType[3] = "slide";
        
        //thời gian của các hoạt động
        timeAttack.put("NONE", new Long(2000));
        timeAttack.put("shooting", new Long(500));
        timeAttack.put("slide", new Long(5000));
        
    }

    public void Update(){
        super.Update();
        
        //luôn luôn hướng về megaman để attack
        if(getGameWorld().megaMan.getPosX() > getPosX())
            setDirection(RIGHT_DIR);
        else setDirection(LEFT_DIR);
        
        if(startTimeForAttacked == 0)
            startTimeForAttacked = System.currentTimeMillis();
        else if(System.currentTimeMillis() - startTimeForAttacked > 300){
            attack();
            startTimeForAttacked = System.currentTimeMillis();
        }
        
        if(!attackType[attackIndex].equals("NONE")){
            //hoạt động bắn
            if(attackType[attackIndex].equals("shooting")){
                
                Bullet bullet = new RocketBullet(getPosX(), getPosY() - 50, getGameWorld());
                if(getDirection() == LEFT_DIR) bullet.setSpeedX(-4);
                else bullet.setSpeedX(4);
                bullet.setTeamType(getTeamType());
                getGameWorld().bulletManager.addObject(bullet);
                
            }else if(attackType[attackIndex].equals("slide"))
            //hoạt động chạy qua chạy lại
            {
                
                if(getGameWorld().physicalMap.haveCollisionWithLeftWall(getBoundForCollisionWithMap())!=null)
                    setSpeedX(5);
                if(getGameWorld().physicalMap.haveCollisionWithRightWall(getBoundForCollisionWithMap())!=null)
                    setSpeedX(-5);
                
                
                setPosX(getPosX() + getSpeedX());
            }
        }else{
            // stop attack
            setSpeedX(0);
        }
        
    }
    //ghi đè các phương thức của lớp cha
    @Override
    public void run() {
        
    }

    @Override
    public void jump() {
        setSpeedY(-5);
    }

    @Override
    public void dick() {
    
    
    }

    @Override
    public void standUp() {
    
    
    }

    @Override
    public void stopRun() {
    
    
    }

    @Override
    public void attack() {
    
        // only switch state attack
        
        if(System.currentTimeMillis() - lastAttackTime > timeAttack.get(attackType[attackIndex])){
            lastAttackTime = System.currentTimeMillis();
            
            attackIndex ++;
            if(attackIndex >= attackType.length) attackIndex = 0;//nếu index > length thì bắt đầu lại từ đầu
            
            if(attackType[attackIndex].equals("slide")){
                if(getPosX() < getGameWorld().megaMan.getPosX()) setSpeedX(5);
                else setSpeedX(-5);
            }
            
        }
    
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        if(attackType[attackIndex].equals("slide")){
            Rectangle rect = getBoundForCollisionWithMap();
            rect.y += 100;
            rect.height -= 100;
            return rect;
        }else
            return getBoundForCollisionWithMap();
    }
    
    //các animation tương ứng với các trạng thái của finalBoss
    @Override
    public void draw(Graphics2D g2) {
        
        if(getState() == NOBEHURT && (System.nanoTime()/10000000)%2!=1)
        {
            System.out.println("Plash...");
        }else{
            
            if(attackType[attackIndex].equals("NONE")){
                if(getDirection() == RIGHT_DIR){
                    animationH.idleforward.Update(System.nanoTime());
                    animationH.idleforward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }else{
                	animationH.idleback.Update(System.nanoTime());
                	animationH.idleback.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }
            }else if(attackType[attackIndex].equals("shooting")){
                
                if(getDirection() == RIGHT_DIR){
                	animationH.shootingforward.Update(System.nanoTime());
                	animationH.shootingforward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }else{
                	animationH.shootingback.Update(System.nanoTime());
                	animationH.shootingback.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY(), g2);
                }
                
            }else if(attackType[attackIndex].equals("slide")){
                if(getSpeedX() > 0){
                	animationH.slideforward.Update(System.nanoTime());
                	animationH.slideforward.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 50, g2);
                }else{
                	animationH.slideback.Update(System.nanoTime());
                	animationH.slideback.draw((int) (getPosX() - getGameWorld().camera.getPosX()), (int) getPosY() - (int) getGameWorld().camera.getPosY() + 50, g2);
                }
            }
        }
    }
    
}