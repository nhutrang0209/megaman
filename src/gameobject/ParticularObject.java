package gameobject;

import state.GameWorldState;
import effect.Animation;
import effect.AnimationHandler;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class ParticularObject extends GameObject {

    public static final int LEAGUE_TEAM = 1;//team megaman
    public static final int ENEMY_TEAM = 2;//team quái
    
    public static final int LEFT_DIR = 0;//hướng trái
    public static final int RIGHT_DIR = 1;//hướng phải

    public static final int ALIVE = 0;//trang thai song
    public static final int BEHURT = 1;//trang thai bi thuong
    public static final int FEY = 2;//trang thai sap chet
    public static final int DEATH = 3;//trang thai chet
    public static final int NOBEHURT = 4;//trang thai sau khi hoi sinh, khong bi tan cong
    private int state = ALIVE;
    
    private float width;//chiều rộng
    private float height;//chiều dài
    private float mass;//cân nặng
    private float speedX;//tốc độ x
    private float speedY;//tốc độ y
    private int blood;//máu
    
    private int damage;//sát thương
    
    private int direction;//hướng
    

    
    protected String name;
    private int teamType;
    
    private long startTimeNoBeHurt;//thời gian bắt đầu không bị thương khi trúng đạn
    private long timeForNoBeHurt;//thời gian không bị thương khi trúng đạn

    public AnimationHandler animationH;
    public ParticularObject(float x, float y, float width, float height,String name, float mass, int blood, GameWorldState gameWorld){

        // posX and posY are the middle coordinate of the object
        super(x, y, gameWorld);
		animationH=new AnimationHandler(name);
		this.name=name;
        setWidth(width);
        setHeight(height);
        setMass(mass);
        setBlood(blood);
        
        direction = RIGHT_DIR;

    }
    
    public void setTimeForNoBehurt(long time){
        timeForNoBeHurt = time;
    }
    
    public long getTimeForNoBeHurt(){
        return timeForNoBeHurt;
    }
    
    public void setState(int state){
        this.state = state;
    }
    
    public int getState(){
        return state;
    }
    
    public void setDamage(int damage){
            this.damage = damage;
    }

    public int getDamage(){
            return damage;
    }

    
    public void setTeamType(int team){
        teamType = team;
    }
    
    public int getTeamType(){
        return teamType;
    }
    
    public void setMass(float mass){
        this.mass = mass;
    }

    public float getMass(){
            return mass;
    }

    public void setSpeedX(float speedX){
        this.speedX = speedX;
    }

    public float getSpeedX(){
        return speedX;
    }

    public void setSpeedY(float speedY){
        this.speedY = speedY;
    }

    public float getSpeedY(){
        return speedY;
    }

    public void setBlood(int blood){
        if(blood>=0)
                this.blood = blood;
        else this.blood = 0;
    }

    public int getBlood(){
        return blood;
    }

    public void setWidth(float width){
        this.width = width;
    }

    public float getWidth(){
        return width;
    }

    public void setHeight(float height){
        this.height = height;
    }

    public float getHeight(){
        return height;
    }
    
    public void setDirection(int dir){
        direction = dir;
    }
    
    public int getDirection(){
        return direction;
    }
    
    public abstract void attack();
    
    //kiem tra xem object co nam trong vung cua camera hay khong
    public boolean isObjectOutOfCameraView(){
        if(getPosX() - getGameWorld().camera.getPosX() > getGameWorld().camera.getWidthView() ||
                getPosX() - getGameWorld().camera.getPosX() < -50
            ||getPosY() - getGameWorld().camera.getPosY() > getGameWorld().camera.getHeightView()
                    ||getPosY() - getGameWorld().camera.getPosY() < -50)
            return true;
        else return false;
    }
    //set vi tri khi nhan vat va cham voi map
    public Rectangle getBoundForCollisionWithMap(){
        Rectangle bound = new Rectangle();
        bound.x = (int) (getPosX() - (getWidth()/2));
        bound.y = (int) (getPosY() - (getHeight()/2));
        bound.width = (int) getWidth();
        bound.height = (int) getHeight();
        return bound;
    }
    
    //ham set lai mau khi nhan vat bi thuong
    public void beHurt(int damgeEat){
        setBlood(getBlood() - damgeEat);
        state = BEHURT;
        hurtingCallback();
    }

    @Override
    public void Update(){
        switch(state){
            case ALIVE://trạng thái sống
                
                // note: SET DAMAGE FOR OBJECT NO DAMAGE
                ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);
                if(object!=null){
                    if(object.getDamage() > 0){

                        // switch state to fey if object die
                        System.out.println("eat damage.... from collision with enemy........ "+object.getDamage());
                        beHurt(object.getDamage());//đối tượng hiện tại behurt
                    }
                    
                }
                
                
                
                break;
                
            case BEHURT://trạng thái đang bị thương
                if(animationH.behurtBackAnim == null){
                    state = NOBEHURT;
                    startTimeNoBeHurt = System.nanoTime();
                    if(getBlood() == 0)
                            state = FEY;
                    
                } else {
                	animationH.behurtForwardAnim.Update(System.nanoTime());
                    if(animationH.behurtForwardAnim.isLastFrame()){
                    	animationH.behurtForwardAnim.reset();
                        state = NOBEHURT;
                        if(getBlood() == 0)
                            state = FEY;
                        startTimeNoBeHurt = System.nanoTime();
                    }
                }
                
                break;
                
            case FEY:
                
                state = DEATH;
                
                break;
            
            case DEATH://trạng thái chết
                
                
                break;
                
            case NOBEHURT://trạng thái sau khi bị bắn
                System.out.println("state = nobehurt");
                if(System.nanoTime() - startTimeNoBeHurt > timeForNoBeHurt)
                    state = ALIVE;
                break;
        }
        
    }
    // vẽ bound khi va chạm với map
    public void drawBoundForCollisionWithMap(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithMap();
        g2.setColor(Color.BLUE);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }
    //ve bound khi va chạm với địch
    public void drawBoundForCollisionWithEnemy(Graphics2D g2){
        Rectangle rect = getBoundForCollisionWithEnemy();
        g2.setColor(Color.RED);
        g2.drawRect(rect.x - (int) getGameWorld().camera.getPosX(), rect.y - (int) getGameWorld().camera.getPosY(), rect.width, rect.height);
    }

    public abstract Rectangle getBoundForCollisionWithEnemy();//lớp bound khi va chạm với địch

    public abstract void draw(Graphics2D g2);
    
    public void hurtingCallback(){};
	
}