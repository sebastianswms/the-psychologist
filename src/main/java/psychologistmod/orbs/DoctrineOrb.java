package psychologistmod.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.FrostOrbPassiveEffect;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbActivateEffect;
import com.megacrit.cardcrawl.vfx.combat.PlasmaOrbPassiveEffect;
import psychologistmod.ThePsychologistMod;
import psychologistmod.actions.AchieveDoctrineAction;

public abstract class DoctrineOrb extends AbstractOrb {
    private float vfxTimer = 1.0F;
    private float vfxIntervalMin = 0.2F;
    private float vfxIntervalMax = 0.7F;
    private static final float ORB_WAVY_DIST = 0.04F;
    private static final float PI_4 = 12.566371F;

    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString("DoctrineOrb");
    public static final String DESCRIPTION[] = orbString.DESCRIPTION;

    protected boolean showChannelValue = true;

    public int baseTimer;
    public int timer;
    public int elapsed;
    public boolean achieving = false;

    public String[] descriptions;

    public DoctrineOrb(String OrbID, String imagePath, int evokeAmount, int timer) {
        this.ID = OrbID;
        this.img = ImageMaster.loadImage(imagePath);

        this.baseTimer = timer;
        this.timer = this.baseTimer;
        this.elapsed = 0;

        this.evokeAmount = evokeAmount;
        this.baseEvokeAmount = this.evokeAmount;

        this.channelAnimTimer = 0.5F;

        this.descriptions = CardCrawlGame.languagePack.getOrbString(this.ID).DESCRIPTION;
        this.name = CardCrawlGame.languagePack.getOrbString(this.ID).NAME;

        updateDescription();
    }

    public void onStartOfTurn(){}

    public void decrementTimer(int amountToDecrement) {
        this.timer -= amountToDecrement;
        this.elapsed += amountToDecrement;
        updateDescription();
        if (this.timer <= 0 && this.achieving == false) {
            this.achieving = true;
            AbstractDungeon.actionManager.addToBottom(new AchieveDoctrineAction(this));
        }
    }

    public void onCardUse(AbstractCard c) {}

    public void onCardDraw(AbstractCard c) {}

    public void onAchieve() {}

    @Override
    public void onEvoke() {}

    @Override
    public void triggerEvokeAnimation()
    {
        CardCrawlGame.sound.play("DUNGEON_TRANSITION", 0.1F);
        AbstractDungeon.effectsQueue.add(new PlasmaOrbActivateEffect(this.cX, this.cY));
    }

    public void applyRigor()
    {
        AbstractPower power = AbstractDungeon.player.getPower("Rigor");
        if (power == null) {
            this.timer = Math.max(0,this.timer);
            return;
        }

        this.timer = Math.max(0, this.baseTimer - power.amount - this.elapsed);
    }

    @Override //if you want to ignore Focus
    public void applyFocus()
    {
        passiveAmount = basePassiveAmount;
        evokeAmount = baseEvokeAmount;
    }

//    public void activateEffect() {
//        float speedTime = 0.6F / AbstractDungeon.player.orbs.size();
//        if (Settings.FAST_MODE) {
//            speedTime = 0.0F;
//        }
//        AbstractDungeon.effectList.add(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA));
//        // AbstractDungeon.actionManager.addToBottom(new VFXAction(new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.PLASMA), speedTime));
//    }

    @Override
    public void playChannelSFX()
    {
        CardCrawlGame.sound.play("AUTOMATON_ORB_SPAWN", 0.1F);
    }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setColor(this.c);
        sb.draw(img, this.cX - 48.0F + this.bobEffect.y / 4.0F, this.cY - 48.0F + this.bobEffect.y / 4.0F, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, 0.0F, 0, 0, 96, 96, false, false);

        renderText(sb);
        this.hb.render(sb);
    }

    @Override
    public void updateAnimation()
    {
        super.updateAnimation();
        this.angle += Gdx.graphics.getDeltaTime() * 45.0F;

        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer < 0.0F)
        {
            AbstractDungeon.effectList.add(new PlasmaOrbPassiveEffect(this.cX, this.cY));
            AbstractDungeon.effectList.add(new FrostOrbPassiveEffect(this.cX, this.cY));
            this.vfxTimer = MathUtils.random(this.vfxIntervalMin, this.vfxIntervalMax);
        }
    }

    @Override
    protected void renderText(SpriteBatch sb)
    {
        if (this.showChannelValue && !this.showEvokeValue) {
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L,
                    Integer.toString(this.timer), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET, Color.PURPLE.cpy(), this.fontScale);
        }
    }

}
