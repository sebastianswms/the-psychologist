package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import psychologistmod.actions.PsychologistChannelAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.orbs.Therapeutic;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Critique extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Critique",
            1,
            CardType.SKILL,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 9;
    private static final int UPG_MAGIC = 4;

    public Critique() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m, p, new WeakPower(m, 1, false), 1, false, AbstractGameAction.AttackEffect.NONE));
        addToBot(new ApplyPowerAction(m, p, new FeedbackPower(m, magicNumber), magicNumber, false, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Critique();
    }
}
