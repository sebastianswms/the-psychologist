package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.actions.BetterDiscardAction;
import psychologistmod.actions.NewLeafAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class NewLeaf extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "NewLeaf",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public NewLeaf() {
        super(cardInfo);
        setExhaust(true);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new BetterDiscardAction(p,p,magicNumber,false));
        addToBot(new NewLeafAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new NewLeaf();
    }
}
