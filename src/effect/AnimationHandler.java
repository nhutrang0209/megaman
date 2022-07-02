package effect;

import gameobject.DarkRaise;
import gameobject.MegaMan;
import gameobject.ParticularObject;
import gameobject.RedEyeDevil;
import gameobject.RobotR;
import gameobject.SmallRedGun;

public class AnimationHandler {

	//MegaMan Animation
	 public Animation runForwardAnim, runBackAnim, runShootingForwarAnim, runShootingBackAnim;
	    public Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim, idleShootingBackAnim;
	    public Animation dickForwardAnim, dickBackAnim;
	    public Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim, flyShootingBackAnim;
	    public Animation landingForwardAnim, landingBackAnim;
	    public Animation behurtBackAnim,behurtForwardAnim;
	    public Animation climWallForward, climWallBack;
	  // Monster Animation  
	    public Animation forwardAnim, backAnim;
	 // Final Animation
	    public Animation idleforward, idleback;
	    public Animation shootingforward, shootingback;
	    public Animation slideforward, slideback;
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
	    	default:
	    		backAnim = CacheDataLoader.getInstance().getAnimation(name);
		        forwardAnim = CacheDataLoader.getInstance().getAnimation(name);
		        forwardAnim.flipAllImage();
	    		break;
	    	}
	    }
//	    public AnimationHandler(MegaMan megaMan) {
//	    	System.out.println("Hello "+megaMan.name);
//	    	}
//
//	    public AnimationHandler(RedEyeDevil redEyeDevil) {
//	    	backAnim = CacheDataLoader.getInstance().getAnimation("redeye");
//	        forwardAnim = CacheDataLoader.getInstance().getAnimation("redeye");
//	        forwardAnim.flipAllImage();
//	    }
//	    
//	    public AnimationHandler(RobotR robotR) {
//	    	 backAnim = CacheDataLoader.getInstance().getAnimation("robotR");
//	         forwardAnim = CacheDataLoader.getInstance().getAnimation("robotR");
//	         forwardAnim.flipAllImage();
//	    }
//	    	
//	    public AnimationHandler(SmallRedGun smallRedGun) {
//	    	backAnim = CacheDataLoader.getInstance().getAnimation("smallredgun");
//	        forwardAnim = CacheDataLoader.getInstance().getAnimation("smallredgun");
//	        forwardAnim.flipAllImage();
//	    }
//	    public AnimationHandler(DarkRaise darkRaise) {
//	    	backAnim = CacheDataLoader.getInstance().getAnimation("darkraise");
//	        forwardAnim = CacheDataLoader.getInstance().getAnimation("darkraise");
//	        forwardAnim.flipAllImage();
//	    }
//	    
}
