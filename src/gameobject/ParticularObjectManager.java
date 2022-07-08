package gameobject;

import state.GameWorldState;
import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ParticularObjectManager {//quản lý tất cả Object trong game

    protected List<ParticularObject> particularObjects;//đưa vào list cac object

    private GameWorldState gameWorld;
    
    public ParticularObjectManager(GameWorldState gameWorld){
        
        particularObjects = Collections.synchronizedList(new LinkedList<ParticularObject>());//khởi tạo đồng bộ list
        this.gameWorld = gameWorld;
        
    }
    
    public GameWorldState getGameWorld(){
        return gameWorld;
    }
    
    //add them object(vi du them vien dan)
    public void addObject(ParticularObject particularObject){
        synchronized(particularObjects){
            particularObjects.add(particularObject);
        }
        
    }
    //xoa 1 object khoi list(vi du vien dan)
    public void RemoveObject(ParticularObject particularObject){
        synchronized(particularObjects){
        
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                if(object == particularObject)
                    particularObjects.remove(id);

            }
        }
    }
    //phương thức tra ve doi tuong va cham voi doi tuong truyen vao
    public ParticularObject getCollisionWidthEnemyObject(ParticularObject object){
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject objectInList = particularObjects.get(id);

                if(object.getTeamType() != objectInList.getTeamType() && 
                        object.getBoundForCollisionWithEnemy().intersects(objectInList.getBoundForCollisionWithEnemy())){
                    return objectInList;
                }
            }
        }
        return null;
    }
    //phương thức update các object
    public void UpdateObjects(){//duyệt qua rồi update từng object
        
        synchronized(particularObjects){
            for(int id = 0; id < particularObjects.size(); id++){
                
                ParticularObject object = particularObjects.get(id);
                
                
                if(!object.isObjectOutOfCameraView()) object.Update();
                
                if(object.getState() == ParticularObject.DEATH){
                    particularObjects.remove(id);
                }
            }
        }

        //System.out.println("Camerawidth  = "+camera.getWidth());
        
    }
    //draw cac object
    public void draw(Graphics2D g2){
        synchronized(particularObjects){
            for(ParticularObject object: particularObjects)
                if(!object.isObjectOutOfCameraView()) object.draw(g2);
        }
    }
	
}