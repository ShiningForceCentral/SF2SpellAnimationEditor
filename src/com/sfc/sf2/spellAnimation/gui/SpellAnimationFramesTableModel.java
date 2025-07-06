/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.spellAnimation.gui;

import com.sfc.sf2.spellAnimation.SpellAnimationFrame;
import com.sfc.sf2.spellAnimation.SpellSubAnimation;
import com.sfc.sf2.spellAnimation.layout.SpellAnimationLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TiMMy
 */
public class SpellAnimationFramesTableModel extends AbstractTableModel {
    
    private final Object[][] tableData;
    private final String[] columns = { "Index", "X", "Y", "Tiles Width", "Tiles Height", "Tile Index", "Foreground" };
    private SpellSubAnimation animation = null;
    private SpellAnimationLayout layout = null;
 
    public SpellAnimationFramesTableModel(SpellSubAnimation animation) {
        super();
        this.animation = animation;
        tableData = new Object[256][];
        int i = 0;
        SpellAnimationFrame[] frames = animation.getFrames();
        if (frames!=null) {
            while (i<frames.length) {
                tableData[i] = new Object[7];
                tableData[i][0] = frames[i].getIndex();
                tableData[i][1] = frames[i].getX();
                tableData[i][2] = frames[i].getY();
                tableData[i][3] = frames[i].getW();
                tableData[i][4] = frames[i].getH();
                tableData[i][5] = frames[i].getTileIndex();
                tableData[i][6] = frames[i].getForeground();
                i++;
            }
        }
        while (i<tableData.length) {
            tableData[i] = new Object[7];
            tableData[i][0] = 0;
            tableData[i][1] = 0;
            tableData[i][2] = 0;
            tableData[i][3] = 0;
            tableData[i][4] = 0;
            tableData[i][5] = 0;
            tableData[i][6] = 0;//true;
            i++;
        }
    }
    
    @Override
    public Class getColumnClass(int column) {
        //if(column == 8) {
        //    return Boolean.class;
        //} else {
            return java.lang.Integer.class;
        //}
    } 
    
    public void updateFrameProperties() {
        List<SpellAnimationFrame> entries = new ArrayList<>();
        for(Object[] entry : tableData) {
            try {
                SpellAnimationFrame frame = new SpellAnimationFrame();
                frame.setIndex((byte)entry[0]);
                frame.setX((byte)entry[1]);
                frame.setY((byte)entry[2]);
                frame.setW((byte)entry[3]);
                frame.setH((byte)entry[4]);
                frame.setTileIndex((byte)entry[5]);
                frame.setForeground((byte)entry[6]);//((Boolean)entry[8]);
                entries.add(frame);
            } catch(Exception e) {
                break;
            }
        }
        SpellAnimationFrame[] returnedEntries = new SpellAnimationFrame[entries.size()];
        animation.setFrames(entries.toArray(returnedEntries));
    }    
    
    @Override
    public Object getValueAt(int row, int col) {
        return tableData[row][col];
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        tableData[row][col] = value;
        updateFrameProperties();
        layout.updateDisplayProperties();
        layout.repaintAnim();
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    @Override
    public int getRowCount() {
        return tableData.length;
    }
 
    @Override
    public int getColumnCount() {
        return columns.length;
    }
 
    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }
    
    public void setLayout(SpellAnimationLayout layout) {
        this.layout = layout;
    }
    
    public void setSpellAnimation(SpellSubAnimation animation) {
        this.animation = animation;
    }
}
