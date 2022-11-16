package psychologistmod.orbs;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;

import psychologistmod.ThePsychologistMod;

import static psychologistmod.ThePsychologistMod.orbPath;

public class Academic extends DoctrineOrb {

    public static final String NAME = "Academic";
    public static final String ORB_ID = ThePsychologistMod.makeID(NAME);
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int EVOKE_AMOUNT = 3;
    private static final int TIMER_AMOUNT = 15;

    public Academic() {
        super(ORB_ID, orbPath(NAME + ".png"), EVOKE_AMOUNT, TIMER_AMOUNT);

        updateDescription();

        angle = MathUtils.random(360.0f); // More Animation-related Numbers
        channelAnimTimer = 0.5f;
    }

    @Override
    public void updateDescription() {
        applyRigor();
        description = DESCRIPTIONS[0] + timer + DESCRIPTIONS [1] + evokeAmount + DESCRIPTIONS[2];
    }

    @Override
    public void onAchieve() {
        if(evokeAmount >= 0){
            AbstractDungeon.actionManager.addToBottom(new ScryAction(evokeAmount));
        }
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(1));
    }

    @Override
    public void onCardDraw(AbstractCard c){
        switch(c.costForTurn){
            case -1:
                this.decrementTimer(EnergyPanel.getCurrentEnergy());
            case -2:
                break;
            default:
                this.decrementTimer(c.costForTurn);
        }
    }

    @Override
    public void triggerEvokeAnimation() {
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ATTACK_FIRE", 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Academic();
    }
}