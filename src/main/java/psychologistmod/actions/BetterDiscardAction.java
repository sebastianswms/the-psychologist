package psychologistmod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;

import java.util.ArrayList;

public class BetterDiscardAction extends AbstractGameAction {
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("DiscardAction");

    public static final String[] TEXT = uiStrings.TEXT;

    private AbstractPlayer p;

    private boolean isRandom;

    private boolean endTurn;

    private AbstractGameAction followUpAction;

    public static ArrayList<AbstractCard> discardedCards = new ArrayList<>();

    private boolean clearDiscardHistory;

    public static int numDiscarded;

    private static final float DURATION = Settings.ACTION_DUR_XFAST;

    public BetterDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom) {
        this(target, source, amount, isRandom, false, true, null);
    }

    public BetterDiscardAction(AbstractCreature target, AbstractCreature source, int amount, boolean isRandom, boolean endTurn, boolean clearDiscardHistory, AbstractGameAction followUpAction) {
        this.p = (AbstractPlayer)target;
        this.isRandom = isRandom;
        setValues(target, source, amount);
        this.actionType = AbstractGameAction.ActionType.DISCARD;
        this.endTurn = endTurn;
        this.duration = DURATION;
        this.clearDiscardHistory = clearDiscardHistory;
        this.followUpAction = followUpAction;
    }

    public void update() {

        if (this.clearDiscardHistory) {
            this.clearDiscardHistory = false;
            discardedCards.clear();
        }
        if (this.duration == DURATION) {
            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() <= this.amount) {
                this.amount = this.p.hand.size();
                int tmp = this.p.hand.size();
                for (int i = 0; i < tmp; i++) {
                    AbstractCard c = this.p.hand.getTopCard();
                    discardedCards.add(c);
                    this.p.hand.moveToDiscardPile(c);
                    if (!this.endTurn)
                        c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(this.endTurn);
                }
                AbstractDungeon.player.hand.applyPowers();
                tickDuration();
                return;
            }
            if (this.isRandom) {
                for (int i = 0; i < this.amount; i++) {
                    AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
                    discardedCards.add(c);
                    this.p.hand.moveToDiscardPile(c);
                    c.triggerOnManualDiscard();
                    GameActionManager.incrementDiscard(this.endTurn);
                }
            } else {
                if (this.amount < 0) {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], 99, true, true);
                    AbstractDungeon.player.hand.applyPowers();
                    tickDuration();
                    return;
                }
                numDiscarded = this.amount;
                if (this.p.hand.size() > this.amount)
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false);
                AbstractDungeon.player.hand.applyPowers();
                tickDuration();
                return;
            }
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            for (AbstractCard c : AbstractDungeon.handCardSelectScreen.selectedCards.group) {
                discardedCards.add(c);
                this.p.hand.moveToDiscardPile(c);
                c.triggerOnManualDiscard();
                GameActionManager.incrementDiscard(this.endTurn);
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        tickDuration();
        if (this.followUpAction != null)
            addToTop(this.followUpAction);
    }
}