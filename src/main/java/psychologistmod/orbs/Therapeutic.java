package psychologistmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbPassiveEffect;

import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import psychologistmod.ThePsychologistMod;

import static psychologistmod.ThePsychologistMod.orbPath;

public class Therapeutic extends DoctrineOrb {

    public static final String NAME = "Therapeutic";
    public static final String ORB_ID = ThePsychologistMod.makeID(NAME);
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;

    private static final int EVOKE_AMOUNT = 9;
    private static final int TIMER_AMOUNT = 3;

    // Animation Rendering Numbers - You can leave these at default, or play around with them and see what they change.
//    private float vfxTimer = 1.0f;
//    private float vfxIntervalMin = 0.1f;
//    private float vfxIntervalMax = 0.4f;
//    private static final float ORB_WAVY_DIST = 0.04f;
//    private static final float PI_4 = 12.566371f;

    public Therapeutic() {
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
        AbstractDungeon.actionManager.addToBottom(new AddTemporaryHPAction(p,p,evokeAmount));
    }

    @Override
    public void onCardUse(AbstractCard c){
        if(c.type == AbstractCard.CardType.ATTACK){
            AbstractPlayer p = AbstractDungeon.player;
            switch(c.costForTurn){
                case -1:
                    this.decrementTimer(c.energyOnUse);
                    break;
                default:
                    this.decrementTimer(c.costForTurn);
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), 0.1f));
            }

            // TODO: Can remove Switch and use c.energyOnUse for all?
        }
    }

//    @Override
//    public void updateAnimation() {// You can totally leave this as is.
//        // If you want to create a whole new orb effect - take a look at conspire's Water Orb. It includes a custom sound, too!
//        super.updateAnimation();
//        angle += Gdx.graphics.getDeltaTime() * 45.0f;
//        vfxTimer -= Gdx.graphics.getDeltaTime();
//        if (vfxTimer < 0.0f) {
//            AbstractDungeon.effectList.add(new DarkOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
//            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
//        }
//    }

    // Render the orb.
//    @Override
//    public void render(SpriteBatch sb) {
//        sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
//        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
//        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
//        sb.setBlendFunction(770, 1);
//        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, -angle, 0, 0, 96, 96, false, false);
//        sb.setBlendFunction(770, 771);
//        renderText(sb);
//        hb.render(sb);
//    }


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
        return new Therapeutic();
    }
}