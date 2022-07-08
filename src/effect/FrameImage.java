package effect;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

// class FrameImage chua cac doi tuong hinh anh phuc vu viec ve animation hoac anh don ra man hinh
public class FrameImage{
    
    private String name;    // ten dung quan ly hinh anh
    private BufferedImage image;    // BufferedImage chua hinh anh
    
    // ham khoi tao co tham so
    public FrameImage(String name, BufferedImage image){
        this.name = name;
        this.image = image;
    }
    
    // ham khoi tao 
    // tao mot doi tuong khac co cung du lieu voi doi tuong cu
    public FrameImage(FrameImage frameImage){
        image = new BufferedImage(frameImage.getWidthImage(), 
                frameImage.getHeightImage(), frameImage.image.getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.image, 0, 0, null);
        name = frameImage.name;
    }
    
    // phuong thuc ve hinh anh ra man hinh
    public void draw(int x, int y, Graphics2D g2){
        
        g2.drawImage(image, x - image.getWidth()/2, y - image.getHeight()/2, null);
        
    }
    
    // ham khoi tao khong co tham so
    public FrameImage(){
        this.name = null;
        image = null;
    }
    
    // lay chieu rong cua buc anh
    public int getWidthImage(){
        return image.getWidth();
    }
    
    // lay chieu cao cua buc anh
    public int getHeightImage(){
        return image.getHeight();
    }
    
    // cac getter, setter dung de truy cap va chinh sua cac thuoc tinh cua hinh anh 
    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
    
    public BufferedImage getImage(){
        return image;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }

}