/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.spellAnimation;

/**
 *
 * @author TiMMy
 */
public class SpellAnimationFrame {

    private int index;
    private int x;
    private int y;
    private int duration;       // Duration / delay
    private int tileIndexBase;  // Tile index base
    private int drawFlags;      // Palette, priority, flip flags
    private int tileMapOffset;  // Tile map offset or index
    private int frameFlags;     // Frame flags (loop, end, etc)
    private int layer;          // FG or BG
    
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTileIndexBase() {
        return tileIndexBase;
    }

    public void setTileIndexBase(int tileIndexBase) {
        this.tileIndexBase = tileIndexBase;
    }

    public int getDrawFlags() {
        return drawFlags;
    }

    public void setDrawFlags(int drawFlags) {
        this.drawFlags = drawFlags;
    }

    public int getTileMapOffset() {
        return tileMapOffset;
    }

    public void setTileMapOffset(int tileMapOffset) {
        this.tileMapOffset = tileMapOffset;
    }

    public int getFrameFlags() {
        return frameFlags;
    }

    public void setFrameFlags(int frameFlags) {
        this.frameFlags = frameFlags;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }
}
