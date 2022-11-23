package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Gaslighting extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Gaslighting",
            1,
            CardType.SKILL,
            CardTarget.ALL_ENEMY,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    public Gaslighting() {
        super(cardInfo, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractPower power = AbstractDungeon.player.getPower(FeedbackPower.POWER_ID);
        if (power != null) {
            int tmp = power.amount;
            if (!this.upgraded) {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot(new LoseHPAction(mo, p, tmp, AbstractGameAction.AttackEffect.FIRE));
                }
            } else {
                for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                    addToBot(new LoseHPAction(mo, p, tmp, AbstractGameAction.AttackEffect.FIRE));
                    addToBot(new ApplyPowerAction(mo, p, new FeedbackPower(mo, tmp), tmp, true, AbstractGameAction.AttackEffect.NONE));
                }
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Gaslighting();
    }
}
