package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.actions.IncreaseCostAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Medication extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Medication",
            0,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 12;
    private static final int UPG_BLOCK = 3;

    private static final int MAGIC = 1;

    public Medication() {
        super(cardInfo);
        setEthereal(true);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new IncreaseCostAction(this.uuid, this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Medication();
    }
}
