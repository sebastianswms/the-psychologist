package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;


public class PsychologistChannelAction extends AbstractGameAction
{
    private AbstractOrb orbType;
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("PsychologistChannelAction");
    public static final String[] TEXT = uiStrings.TEXT;

    public PsychologistChannelAction(AbstractOrb OrbType)
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.orbType = OrbType;
    }

    public void update()
    {
        if (AbstractDungeon.player.maxOrbs == 10 && !AbstractDungeon.player.hasEmptyOrb()) {
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 2.0F, TEXT[0], true));
            this.isDone = true;
            return;
        }

        if(AbstractDungeon.player.maxOrbs < 10){
            AbstractDungeon.player.increaseMaxOrbSlots(1, true);
        }
        AbstractDungeon.player.channelOrb(this.orbType);

        tickDuration();
        this.isDone = true;
        return;
    }
}