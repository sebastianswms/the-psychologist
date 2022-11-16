package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class NewLeafAction extends AbstractGameAction {

    public NewLeafAction() {
        this.duration = 0.0F;
        this.actionType = AbstractGameAction.ActionType.WAIT;
    }

    public void update() {
        AbstractCreature p = AbstractDungeon.player;
        int amount = 0;
        for (AbstractCard c : DrawCardAction.drawnCards) {
            switch(c.costForTurn){
                case -1:
                    amount += EnergyPanel.getCurrentEnergy();
                    break;
                case -2:
                    break;
                default:
                    amount += c.costForTurn;
            }
            break;
        }
        for (AbstractCard c : BetterDiscardAction.discardedCards) {
            switch(c.costForTurn){
                case -1:
                    amount += EnergyPanel.getCurrentEnergy();
                    break;
                case -2:
                    break;
                default:
                    amount += c.costForTurn;
            }
            break;
        }
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new VigorPower(p, amount), amount));
        this.isDone = true;
    }
}
