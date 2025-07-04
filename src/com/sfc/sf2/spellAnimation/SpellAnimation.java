/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sfc.sf2.spellAnimation;

import com.sfc.sf2.spellGraphic.SpellGraphic;

/**
 *
 * @author TiMMy
 */
public class SpellAnimation {
    
    private SpellGraphic spellGraphic;
    private SpellSubAnimation[] spellSubAnimation;

    public SpellGraphic getSpellGraphic() {
        return spellGraphic;
    }

    public void setSpellGraphic(SpellGraphic spellGraphic) {
        this.spellGraphic = spellGraphic;
    }

    public SpellSubAnimation[] getSpellSubAnimation() {
        return spellSubAnimation;
    }

    public void setSpellSubAnimation(SpellSubAnimation[] spellSubAnimation) {
        this.spellSubAnimation = spellSubAnimation;
    }
}
