package psychologistmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;

import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import psychologistmod.ThePsychologistMod;

import static psychologistmod.ThePsychologistMod.orbPath;

public class Practical extends DoctrineOrb {

    public static final String NAME = "Practical";
    public static final String ORB_ID = ThePsychologistMod.makeID(NAME);
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int EVOKE_AMOUNT = 9;
    private static final int TIMER_AMOUNT = 3;

    public Practical() {
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
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,new VigorPower(p, this.evokeAmount), this.evokeAmount));
    }

    @Override
    public void onCardUse(AbstractCard c){
        if(c.type == AbstractCard.CardType.SKILL){
            AbstractPlayer p = AbstractDungeon.player;
            switch(c.costForTurn){
                case -1:
                    this.decrementTimer(c.energyOnUse);
                    break;
                default:
                    this.decrementTimer(c.costForTurn);
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.FROST), 0.1f));
            }

            // TODO: Can remove Switch and use c.energyOnUse for all?
        }
    }

    @Override
    public void triggerEvokeAnimation() { // The evoke animation of this orb is the dark-orb activation effect.
        AbstractDungeon.effectsQueue.add(new DarkOrbActivateEffect(cX, cY));
    }

    @Override
    public void playChannelSFX() { // When you channel this orb, the ATTACK_FIRE effect plays ("Fwoom").
        CardCrawlGame.sound.play("ATTACK_FIRE", 0.1f);
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Practical();
    }
}