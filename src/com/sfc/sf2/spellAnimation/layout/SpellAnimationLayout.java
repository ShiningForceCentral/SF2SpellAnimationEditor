/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.spellAnimation.layout;

import com.sfc.sf2.background.Background;
import com.sfc.sf2.background.layout.BackgroundLayout;
import com.sfc.sf2.spellAnimation.SpellAnimation;
import com.sfc.sf2.ground.Ground;
import com.sfc.sf2.ground.layout.GroundLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author TiMMy
 */
public class SpellAnimationLayout extends JPanel {
    
    private static final int DEFAULT_TILES_PER_ROW = 32;
    
    private static final int BACKGROUND_BASE_X = 0;
    private static final int BACKGROUND_BASE_Y = 56;
    private static final int GROUND_BASE_X = 136;
    private static final int GROUND_BASE_Y = 140;
    
    private int tilesPerRow = DEFAULT_TILES_PER_ROW;
    
    private Background background;
    private Ground ground;
    private SpellAnimation spellAnimation;
    private SpellAnimation animation;
    
    private BufferedImage backgroundImage = null;
    private BufferedImage groundImage = null;
    private BufferedImage[] spellAnimationImages = null;
    
    private int currentDisplaySize = 1;
    private int currentAnimationFrame = 0;
    private int currentFrame = 0;
    private int currentFrameX = 0;
    private int currentFrameY = 0;
    
    private javax.swing.JPanel panel;
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);   
        g.drawImage(buildImage(), 0, 0, this);       
    }
    
    public BufferedImage buildImage() {
        BufferedImage image = buildImage(false, false);
        setSize(image.getWidth(), image.getHeight());
        return image;
    }
    
    public BufferedImage buildImage(boolean pngExport, boolean spellAnimationOnly) {
        
        BufferedImage image = new BufferedImage(256, 224, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        
        g.drawImage(backgroundImage, BACKGROUND_BASE_X, BACKGROUND_BASE_Y, null);
        g.drawImage(groundImage, GROUND_BASE_X, GROUND_BASE_Y, null);
        g.drawImage(spellAnimationImages[currentFrame], currentFrameX, currentFrameY, null);
        
        return resize(image);
    }  
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }
    
    public int getTilesPerRow() {
        return tilesPerRow;
    }

    public void setTilesPerRow(int tilesPerRow) {
        this.tilesPerRow = tilesPerRow;
    }

    public void setBackground(Background background) {
        this.background = background;
        BackgroundLayout backgroundLayout = new BackgroundLayout();
        backgroundLayout.setTiles(background.getTiles());
        backgroundImage = backgroundLayout.buildImage();        
    }

    public void setGround(Ground ground) {
        this.ground = ground;
        GroundLayout groundLayout = new GroundLayout();
        groundLayout.setTiles(ground.getTiles());        
        groundImage = groundLayout.buildImage();
    }

    public void setSpellAnimation(SpellAnimation spellAnimation) {
        this.spellAnimation = spellAnimation;
        generateSpellAnimationImages();
    }
    
    public void generateSpellAnimationImages() {
        SpellAnimationLayout spellAnimationLayout = new SpellAnimationLayout();
        spellAnimationImages = new BufferedImage[spellAnimation.getSpellSubAnimation()[0].getFrames().length];
        for(int i=0;i<spellAnimation.getSpellSubAnimation()[0].getFrames().length;i++) {
            //spellAnimationLayout.setTilesPerRow(spellAnimation.getFrames()[i]);
            spellAnimationImages[i] = spellAnimationLayout.buildImage();
        }
    }
    
    private static BufferedImage flipH(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(image, image.getWidth(), 0, -image.getWidth(), image.getHeight(), null);
        g.dispose();
        return newImage;
    }

    private static BufferedImage flipV(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(image, 0, image.getHeight(), image.getWidth(), -image.getHeight(), null);
        g.dispose();
        return newImage;
    }
    
    private BufferedImage resize(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth()*currentDisplaySize, image.getHeight()*currentDisplaySize, BufferedImage.TYPE_INT_ARGB);
        Graphics g = newImage.getGraphics();
        g.drawImage(image, 0, 0, image.getWidth()*currentDisplaySize, image.getHeight()*currentDisplaySize, null);
        g.dispose();
        return newImage;
    }

    public void setCurrentBattlespriteFrame(int currentBattlespriteFrame) {
        this.currentFrame = currentBattlespriteFrame;
    }

    public int getCurrentFrameX() {
        return currentFrameX;
    }

    public void setCurrentFrameX(int currentFrameX) {
        this.currentFrameX = currentFrameX;
    }

    public int getCurrentFrameY() {
        return currentFrameY;
    }

    public void setCurrentFrameY(int currentFrameY) {
        this.currentFrameY = currentFrameY;
    }

    public void setAnimation(SpellAnimation animation) {
        this.animation = animation;
    }
    
    public void updateDisplayProperties() {
        if (this.currentAnimationFrame==0) {
            this.currentFrame = 0;
            this.currentFrameX = 0;
            this.currentFrameY = 0;
        } else {
            int bsFrame = animation.getSpellSubAnimation()[0].getFrames()[this.currentAnimationFrame - 1].getIndex();
            if(bsFrame == 0xF){
                this.currentFrame = getPreviousBattlespriteFrame(this.currentAnimationFrame - 1);
            } else {
                this.currentFrame = bsFrame;
            }
            this.currentFrameX = animation.getSpellSubAnimation()[0].getFrames()[this.currentAnimationFrame - 1].getX();
            this.currentFrameY = animation.getSpellSubAnimation()[0].getFrames()[this.currentAnimationFrame - 1].getY();
        }
    }
    
    private int getPreviousBattlespriteFrame(int initAnimFrame) {
        while (animation.getSpellSubAnimation()[0].getFrames()[initAnimFrame].getIndex()==0xF) {
            initAnimFrame--;
        }
        return animation.getSpellSubAnimation()[0].getFrames()[initAnimFrame].getIndex();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public int getCurrentDisplaySize() {
        return currentDisplaySize;
    }

    public void setCurrentDisplaySize(int currentDisplaySize) {
        this.currentDisplaySize = currentDisplaySize;
    }

    public int getCurrentAnimationFrame() {
        return currentAnimationFrame;
    }

    public void setCurrentAnimationFrame(int currentAnimationFrame) {
        this.currentAnimationFrame = currentAnimationFrame;
    }
}
