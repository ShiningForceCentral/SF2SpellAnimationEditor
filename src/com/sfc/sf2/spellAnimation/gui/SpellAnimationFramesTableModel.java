/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.spellAnimation.gui;

import com.sfc.sf2.spellAnimation.SpellAnimation;
import com.sfc.sf2.spellAnimation.SpellAnimationFrame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TiMMy
 */
public class SpellAnimationFramesTableModel extends AbstractTableModel {
    
    private final Object[][] tableData;
    private final String[] columns = { "Index", "Duration", "X", "Y", "H Flip", "V Flip", "Z" };
    private SpellAnimation animation = null;
 
    public SpellAnimationFramesTableModel(SpellAnimation animation) {
        super();
        this.animation = animation;
        tableData = new Object[256][];
        int i = 0;
        SpellAnimationFrame[] frames = animation.getSpellSubAnimation()[0].getFrames();
        if (frames!=null) {
            while (i<frames.length) {
                tableData[i] = new Object[10];
                tableData[i][0] = Integer.toString(frames[i].getIndex());
                tableData[i][1] = Integer.toString(frames[i].getDuration());
                tableData[i][2] = Integer.toString(frames[i].getX());
                tableData[i][3] = Integer.toString(frames[i].getY());
                i++;
            }
        }
        while (i<tableData.length) {
            tableData[i] = new Object[10];
            tableData[i][0] = "";
            tableData[i][1] = "";
            tableData[i][2] = "0";
            tableData[i][3] = "0";
            tableData[i][4] = "0";
            tableData[i][5] = false;
            tableData[i][6] = false;
            tableData[i][7] = false;
            tableData[i][8] = "0";
            tableData[i][9] = "0";
            i++;
        }
    }
    
    @Override
    public Class getColumnClass(int column) {
        if(column == 5 || column == 6 || column == 7) {
            return Boolean.class;
        } else {
            return String.class;
        }
    } 
    
    public void updateFrameProperties() {
        List<SpellAnimationFrame> entries = new ArrayList<>();
        for(Object[] entry : tableData) {
            try {
                SpellAnimationFrame frame = new SpellAnimationFrame();
                frame.setIndex(Integer.parseInt((String)entry[0]));
                frame.setDuration(Integer.parseInt((String)entry[1]));
                frame.setX(Integer.parseInt((String)entry[2]));
                frame.setY(Integer.parseInt((String)entry[3]));
                entries.add(frame);
            } catch(Exception e) {
                break;
            }
        }
        SpellAnimationFrame[] returnedEntries = new SpellAnimationFrame[entries.size()];
        animation.getSpellSubAnimation()[0].setFrames(entries.toArray(returnedEntries));
    }    
    
    @Override
    public Object getValueAt(int row, int col) {
        return tableData[row][col];
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        tableData[row][col] = value;
        updateFrameProperties();
        //animation.getLayout().updateDisplayProperties();
        //animation.getLayout().getPanel().revalidate();
        //animation.getLayout().getPanel().repaint();
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
}
