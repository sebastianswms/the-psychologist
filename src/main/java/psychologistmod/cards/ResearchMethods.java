package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.powers.RigorPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class ResearchMethods extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "ResearchMethods",
            1,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 2;
    private static final int UPG_MAGIC = 1;

    public ResearchMethods() {
        super(cardInfo);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p, new RigorPower(p,magicNumber),magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new ResearchMethods();
    }
}
