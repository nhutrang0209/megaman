package effect;

import gameobject.MegaMan;
import gameobject.ParticularObject;
import gameobject.RedEyeDevil;
import gameobject.RobotR;
import gameobject.SmallRedGun;

import java.awt.Graphics2D;

import gameobject.BlueFire;
import gameobject.GameObject;

public class AnimationHandler {

	//MegaMan Animation
	 public Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
	    public Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
	    public Animation dickForwardAnim, dickBackAnim;
	    public Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
	    public Animation landingForwardAnim, landingBackAnim;
	    public Animation behurtBackAnim,behurtForwardAnim;
	    public Animation climWallForward, climWallBack;
	  //BlueFire Animation
	    public Animation  forwardBulletAnim, backBulletAnim;
	  // Monster Animation  
	    public Animation forwardAnim, backAnim;
	 // FinalBoss Animation
	    public Animation idleforward, idleback;
	    public Animation shootingforward, shootingback;
	    public Animation slideforward, slideback;
	    
	    public Animation forwardBulletAnimUp, forwardBulletAnimDown;
	    public Animation backBulletAnimUp, backBulletAnimDown;
	    public AnimationHandler(String name) {
	    	switch(name) {
	    	case "megaMan":
	    		runForwardAnim = CacheDataLoader.getInstance().getAnimation("run");
		        runBackAnim = CacheDataLoader.getInstance().getAnimation("run");
		        runBackAnim.flipAllImage();   
		        
		        idleForwardAnim = CacheDataLoader.getInstance().getAnimation("idle");
		        idleBackAnim = CacheDataLoader.getInstance().getAnimation("idle");
		        idleBackAnim.flipAllImage();
		        
		        dickForwardAnim = CacheDataLoader.getInstance().getAnimation("dick");
		        dickBackAnim = CacheDataLoader.getInstance().getAnimation("dick");
		        dickBackAnim.flipAllImage();
		        
		        flyForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
		        flyForwardAnim.setIsRepeated(false);
		        flyBackAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
		        flyBackAnim.setIsRepeated(false);
		        flyBackAnim.flipAllImage();
		        
		        landingForwardAnim = CacheDataLoader.getInstance().getAnimation("landing");
		        landingBackAnim = CacheDataLoader.getInstance().getAnimation("landing");
		        landingBackAnim.flipAllImage();
		        
		        climWallBack = CacheDataLoader.getInstance().getAnimation("clim_wall");
		        climWallForward = CacheDataLoader.getInstance().getAnimation("clim_wall");
		        climWallForward.flipAllImage();
		        
		        behurtForwardAnim = CacheDataLoader.getInstance().getAnimation("hurting");
		        behurtBackAnim = CacheDataLoader.getInstance().getAnimation("hurting");
		        behurtBackAnim.flipAllImage();
		        
		        idleShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
		        idleShootingBackAnim = CacheDataLoader.getInstance().getAnimation("idleshoot");
		        idleShootingBackAnim.flipAllImage();
		        
		        runShootingForwarAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
		        runShootingBackAnim = CacheDataLoader.getInstance().getAnimation("runshoot");
		        runShootingBackAnim.flipAllImage();
		        
		        flyShootingForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
		        flyShootingBackAnim = CacheDataLoader.getInstance().getAnimation("flyingupshoot");
		        flyShootingBackAnim.flipAllImage();
	    		break;
	    	case "finalBoss":
	    		idleback = CacheDataLoader.getInstance().getAnimation("boss_idle");
	            idleforward = CacheDataLoader.getInstance().getAnimation("boss_idle");
	            idleforward.flipAllImage();
	            
	            shootingback = CacheDataLoader.getInstance().getAnimation("boss_shooting");
	            shootingforward = CacheDataLoader.getInstance().getAnimation("boss_shooting");
	            shootingforward.flipAllImage();
	            
	            slideback = CacheDataLoader.getInstance().getAnimation("boss_slide");
	            slideforward = CacheDataLoader.getInstance().getAnimation("boss_slide");
	            slideforward.flipAllImage();
	    		break;
	    	case "bluefire":
	            forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
	            backBulletAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
	            backBulletAnim.flipAllImage();
	            break;
	    	case "redeyebullet":
	    		forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");//tạo animation
	            backBulletAnim = CacheDataLoader.getInstance().getAnimation("redeyebullet");
	            backBulletAnim.flipAllImage();
	            break;
	    	case "yellow_flower_bullet":
	    		forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("yellow_flower_bullet");//tạo animation
	            backBulletAnim = CacheDataLoader.getInstance().getAnimation("yellow_flower_bullet");
	            backBulletAnim.flipAllImage();
	            break;
	    	case "robotRbullet":
	    		forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("robotRbullet");//tạo animation
	            backBulletAnim = CacheDataLoader.getInstance().getAnimation("robotRbullet");
	            backBulletAnim.flipAllImage();
	            break;
	    	case "rocket":
	    		backBulletAnimUp = CacheDataLoader.getInstance().getAnimation("rocketUp");
	            backBulletAnimDown = CacheDataLoader.getInstance().getAnimation("rocketDown");
	            backBulletAnim = CacheDataLoader.getInstance().getAnimation("rocket");
	            
	            forwardBulletAnimUp = CacheDataLoader.getInstance().getAnimation("rocketUp");
	            forwardBulletAnimUp.flipAllImage();
	            forwardBulletAnimDown = CacheDataLoader.getInstance().getAnimation("rocketDown");
	            forwardBulletAnimDown.flipAllImage();
	            forwardBulletAnim = CacheDataLoader.getInstance().getAnimation("rocket");
	            forwardBulletAnim.flipAllImage();

	            break; 
	    	default:
	    		backAnim = CacheDataLoader.getInstance().getAnimation(name);
		        forwardAnim = CacheDataLoader.getInstance().getAnimation(name);
		        forwardAnim.flipAllImage();
	    		break;
	    	}
	    }
	    
	    public void MegaLanding() {
	    	 landingBackAnim.reset();
             runForwardAnim.reset();
             runBackAnim.reset();
	    }
	    
	    public void MegaStandup() {
	        idleForwardAnim.reset();
	        idleBackAnim.reset();
	        dickForwardAnim.reset();
	        dickBackAnim.reset();
	    }

	    public void MegaStoprun() {
	    	runForwardAnim.reset();
	        runBackAnim.reset();
	        runForwardAnim.unIgnoreFrame(0);
	        runBackAnim.unIgnoreFrame(0);
	    }
	    
	    public void Megaflyreset() {
            flyBackAnim.reset();
            flyForwardAnim.reset();
	    }
	    
	    public void BulletFIgnore012() {
	    	forwardBulletAnim.setIgnoreFrame(0);
        	forwardBulletAnim.setIgnoreFrame(1);
        	forwardBulletAnim.setIgnoreFrame(2);
	    }
	    
	    public void BulletBIgnore012() {
	    	backBulletAnim.setIgnoreFrame(0);
        	backBulletAnim.setIgnoreFrame(1);
        	backBulletAnim.setIgnoreFrame(2);
	    }
	    public boolean BulletFMoving() {
	    	return !forwardBulletAnim.isIgnoreFrame(0) && forwardBulletAnim.getCurrentFrame() == 3;
	    }
	    public boolean BulletBMoving() {
	    	return !backBulletAnim.isIgnoreFrame(0) && backBulletAnim.getCurrentFrame() == 3;
	    }
	    
}
