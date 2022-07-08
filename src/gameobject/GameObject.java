package gameobject;

import state.GameWorldState;

public abstract class GameObject {// các đối tượng lớn

	private float posX, posY;
	
	private GameWorldState gameWorld;
	
	public GameObject(float x, float y, GameWorldState gameWorld){
		posX = x;
		posY = y;
		this.gameWorld = gameWorld;
	}
	
	public void setPosX(float x){
		posX = x;
	}
	
	public float getPosX(){
		return posX;
	}
	
	public void setPosY(float y){
		posY = y;
	}
	
	public float getPosY(){
		return posY;
	}
	
	public GameWorldState getGameWorld(){
		return gameWorld;
	}
	
	public abstract void Update();//ai kế thừa thì tự viết lại 
	
}
