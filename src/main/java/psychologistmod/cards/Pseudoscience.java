package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.powers.PseudosciencePower;
import psychologistmod.powers.RigorPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Pseudoscience extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Pseudoscience",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.RARE,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 12;
    public static final int UPG_MAGIC = 4;

    public Pseudoscience() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RigorPower(p, this.magicNumber), this.magicNumber));
        addToBot(new ApplyPowerAction(p, p, new PseudosciencePower(p, 3), 3));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Pseudoscience();
    }
}
