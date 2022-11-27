package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class RoughDraft extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RoughDraft",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 7;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public RoughDraft() {
        super(cardInfo);
        setBlock(BLOCK);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,p,block));
        addToBot(new ApplyPowerAction(p,p, new DrawCardNextTurnPower(p,magicNumber),magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new RoughDraft();
    }
}
