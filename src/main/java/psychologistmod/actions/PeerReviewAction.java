package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.powers.RigorPower;

public class PeerReviewAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    public void update() {
        if (this.p.hasPower(RigorPower.POWER_ID)) {
            int rigorAmount = (this.p.getPower(RigorPower.POWER_ID)).amount;
            addToTop(new ApplyPowerAction(this.p, this.p, new RigorPower(this.p, rigorAmount), rigorAmount));
        }
        if (this.p.hasPower(FeedbackPower.POWER_ID)) {
            int feedbackAmount = (this.p.getPower(FeedbackPower.POWER_ID)).amount;
            addToTop(new ApplyPowerAction(this.p, this.p, new FeedbackPower(this.p, feedbackAmount), feedbackAmount));
        }
        tickDuration();
        this.isDone = true;
    }
}
