package effect;


import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// lop Animation là lop chua cac doi tuong hinh anh dong
// gom nhieu frameimage sap xep theo mot thu tu de tao ra hinh anh dong
public class Animation {
    
    private String name;// ten dung quan ly animation
    
    private boolean isRepeated; // dung de lap lai animation
    
    private ArrayList<FrameImage> frameImages; // chua cac doi tuong frameimage de tao animation
    private int currentFrame; // danh dau frameimage hien tai
    
    private ArrayList<Boolean> ignoreFrames; // danh dau frameimage can bo di
    
    private ArrayList<Double> delayFrames; // thoi gian tre giua cac frameimage
    private long beginTime; // danh dau thoi gian bat dau 1 frameimage

    private boolean drawRectFrame;
    
    // ham khoi tao
    public Animation(){
        delayFrames = new ArrayList<Double>();
        beginTime = 0;
        currentFrame = 0;

        ignoreFrames = new ArrayList<Boolean>();
        
        frameImages = new ArrayList<FrameImage>();
        
        drawRectFrame = false;
        
        isRepeated = true;
    }
    // ham khoi tao
    // tao mot animation khac co cung du lieu voi animation da tao
    public Animation(Animation animation){
        
        beginTime = animation.beginTime;
        currentFrame = animation.currentFrame;
        drawRectFrame = animation.drawRectFrame;
        isRepeated = animation.isRepeated;
        
        delayFrames = new ArrayList<Double>();
        for(Double d : animation.delayFrames){
            delayFrames.add(d);
        }
        
        ignoreFrames = new ArrayList<Boolean>();
        for(boolean b : animation.ignoreFrames){
            ignoreFrames.add(b);
        }
        
        frameImages = new ArrayList<FrameImage>();
        for(FrameImage f : animation.frameImages){
            frameImages.add(new FrameImage(f));
        }
    }
    
    // phuong thuc dung de lap lai animation
    public void setIsRepeated(boolean isRepeated){
        this.isRepeated = isRepeated;
    }
    // kiem tra animation co lap lai hay khong
    public boolean getIsRepeated(){
        return isRepeated;
    }
    // kiem tra framimage bi bo ra
    public boolean isIgnoreFrame(int id){
        return ignoreFrames.get(id);
    }
    // bo frameimage ra khoi animation
    public void setIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, true);
    }
    // dat lai frameimage da bo ra
    public void unIgnoreFrame(int id){
        if(id >= 0 && id < ignoreFrames.size())
            ignoreFrames.set(id, false);
    }
    
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    
    // cap nhat framgeimage hien tai cho animation
    public void setCurrentFrame(int currentFrame){
        if(currentFrame >= 0 && currentFrame < frameImages.size())
            this.currentFrame = currentFrame;
        else this.currentFrame = 0;
    }
    public int getCurrentFrame(){
        return this.currentFrame;
    }
    // phuong thuc dat lai animation
    public void reset(){
        currentFrame = 0;
        beginTime = 0;
    }
    // them frameimage cho animation
    public void add(FrameImage frameImage, double timeToNextFrame){

        ignoreFrames.add(false);
        frameImages.add(frameImage);
        // them thoi gian de chuyen den frameimage tiep theo
        delayFrames.add(new Double(timeToNextFrame));
        
    }
    // dung de ve hinh chu nhat chua hinh anh
    public void setDrawRectFrame(boolean b){
        drawRectFrame = b;
    }

    // tra ve frameimage hien tai
    public BufferedImage getCurrentImage(){
        return frameImages.get(currentFrame).getImage();
    }
    
    // cap nhat frameimage moi cho animation
    public void Update(long deltaTime){
        
        if(beginTime == 0) beginTime = deltaTime; // cap nhat thoi gian bat dau
        else{
            
            if(deltaTime - beginTime > delayFrames.get(currentFrame)){
                nextFrame();
                beginTime = deltaTime; // cap nhat lai thoi gian bat dau
            }
        }
        
    }
    
    // phuong thuc chuyen den frameimage tiep theo
    private void nextFrame(){
        
        if(currentFrame >= frameImages.size() - 1){
            
            if(isRepeated) currentFrame = 0;
        }
        else currentFrame++;
        
        if(ignoreFrames.get(currentFrame)) nextFrame();
        
    }
    // kiem tra xem co phai frameimage cuoi hay khong
    public boolean isLastFrame(){
        if(currentFrame == frameImages.size() - 1)
            return true;
        else return false;
    }
 
    // phuong thuc dung de dao tat ca cac hinh anh trong animation
    public void flipAllImage(){
        // duyet tat ca frameimage trong animation
        for(int i = 0;i < frameImages.size(); i++){
            // lay ra frameimage thu i
            BufferedImage image = frameImages.get(i).getImage();
            
            // dao frameimage da lay
            AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
            tx.translate(-image.getWidth(), 0);

            AffineTransformOp op = new AffineTransformOp(tx,
            AffineTransformOp.TYPE_BILINEAR);
            image = op.filter(image, null);
            
            // tra frameimage lai frameImages
            frameImages.get(i).setImage(image);
            
        }
        
    }
    
    // ve ra man hinh frameimage hien tai
    public void draw(int x, int y, Graphics2D g2){
        // lay ra frameimage hien tai
        BufferedImage image = getCurrentImage();
        
        // ve ra man hinh
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        if(drawRectFrame)
            g2.drawRect(x - image.getWidth()/2, x - image.getWidth()/2, image.getWidth(), image.getHeight());
        
    }
    
}