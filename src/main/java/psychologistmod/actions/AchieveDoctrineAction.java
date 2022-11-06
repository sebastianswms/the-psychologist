package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import psychologistmod.orbs.DoctrineOrb;

import java.util.Collections;

public class AchieveDoctrineAction extends AbstractGameAction {

    private DoctrineOrb orb;

    public AchieveDoctrineAction(DoctrineOrb orbToAchieve)
    {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.SPECIAL;
        this.orb = orbToAchieve;
    }

    @Override
    public void update()
    {
        orb.onAchieve();
        AbstractPlayer p = AbstractDungeon.player;

        for (int i = p.orbs.indexOf(this.orb)+1; i < p.orbs.size(); i++) {
            Collections.swap(p.orbs, i, i - 1);
        }
        AbstractDungeon.player.decreaseMaxOrbSlots(1);

        tickDuration();
        this.isDone = true;
        return;
    }
}
