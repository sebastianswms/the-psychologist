package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MindMapAction extends AbstractGameAction {

    public MindMapAction() {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        int count = AbstractDungeon.player.hand.size();

        if (Settings.FAST_MODE) {
            addToTop(new ExhaustAction(count, true, true, false, Settings.ACTION_DUR_XFAST));
        } else {
            addToTop(new ExhaustAction(count, true, true));
        }

        addToBot(new MakeTempCardInHandAction(new Insight(), count));
        this.isDone = true;
    }
}
