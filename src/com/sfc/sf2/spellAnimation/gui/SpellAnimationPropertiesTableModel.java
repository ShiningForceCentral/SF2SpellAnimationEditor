/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.spellAnimation.gui;

import com.sfc.sf2.spellAnimation.SpellAnimation;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author TiMMy
 */
public class SpellAnimationPropertiesTableModel extends AbstractTableModel {
    
    private final Object[][] tableData;
    private final String[] columns = { "Frame Number", "Spell Init Frame", "Spell Anim", "End Spell", "H Flip", "V Flip", "Idle 1 Z" };
    private SpellAnimation animation = null;
    
    public SpellAnimationPropertiesTableModel(SpellAnimation animation) {
        super();
        this.animation = animation;
        tableData = new Object[1][];
        int i = 0;
        if(animation!=null){
            tableData[i] = new Object[10];
            //tableData[i][0] = Integer.toString(animation.getFrameNumber());
            //tableData[i][1] = Integer.toString(animation.getSpellInitFrame());
            //tableData[i][2] = Integer.toString(animation.getSpellAnim());
            //tableData[i][3] = (animation.getEndSpellAnim()!=0);
        }
    }
    
    public void updateProperties() {
        //animation.setFrameNumber(Integer.parseInt((String)tableData[0][0]));
        //animation.setSpellInitFrame(Integer.parseInt((String)tableData[0][1]));
        //animation.setSpellAnim(Integer.parseInt((String)tableData[0][2]));
        //animation.setEndSpellAnim(((Boolean)tableData[0][3])?-1:0);
    }
    
    @Override
    public Class getColumnClass(int column) {
        if(column == 3 || column == 5 || column == 6 || column == 7) {
            return Boolean.class;
        } else {
            return String.class;
        }
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        return tableData[row][col];
    }
    
    @Override
    public void setValueAt(Object value, int row, int col) {
        tableData[row][col] = value;
        updateProperties();
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
