package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.powers.RigorPower;

public class PeerReviewAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    public void update() {
        if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower("Rigor")) {
            int rigorAmount = (this.p.getPower("Rigor")).amount;
            addToTop(new ApplyPowerAction(this.p, this.p, new RigorPower(this.p, rigorAmount), rigorAmount));
        }
        if (this.duration == Settings.ACTION_DUR_XFAST && this.p.hasPower("Feedback")) {
            int feedbackAmount = (this.p.getPower("Feedback")).amount;
            addToTop(new ApplyPowerAction(this.p, this.p, new FeedbackPower(this.p, feedbackAmount), feedbackAmount));
        }
        tickDuration();
    }
}
