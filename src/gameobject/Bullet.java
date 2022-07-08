package gameobject;

import state.GameWorldState;
import java.awt.Graphics2D;

public abstract class Bullet extends ParticularObject {

    public Bullet(float x, float y, float width, float height,String name, float mass, int damage, GameWorldState gameWorld) {
            super(x, y, width, height,name, mass, 1, gameWorld);
            setDamage(damage);//sát thương đối tượng đó gây ra khi chạm vào
    }
    
    public abstract void draw(Graphics2D g2d);//mỗi loại đạn sẽ có hình khác nhau

    public void Update(){
        super.Update();
        setPosX(getPosX() + getSpeedX());// xét tọa độ x,y thay đổi thông qua tốc độ
        setPosY(getPosY() + getSpeedY());
        ParticularObject object = getGameWorld().particularObjectManager.getCollisionWidthEnemyObject(this);//kiểm tra xem đạn có va chạm vs enermy
        if(object!=null && object.getState() == ALIVE){//chạm vào enermy còn sống
            setBlood(0);//mất viên đạn
            System.out.println(object);
            object.beHurt(getDamage());//obj bị trừ máu
            System.out.println("Bullet set behurt for enemy");
        }
    }
    
}
