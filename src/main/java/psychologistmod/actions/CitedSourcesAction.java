package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CitedSourcesAction extends AbstractGameAction {
    private int scryAmount;

    public CitedSourcesAction(int scryAmount) {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.scryAmount = scryAmount;
    }

    public void update() {
        for (AbstractCard c : DrawCardAction.drawnCards) {
            if (c.color == AbstractCard.CardColor.COLORLESS) {
                AbstractDungeon.actionManager.addToTop(new ScryAction(scryAmount));
                break;
            }
        }
        this.isDone = true;
    }
}
