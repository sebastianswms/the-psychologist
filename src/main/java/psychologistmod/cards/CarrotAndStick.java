package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class CarrotAndStick extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "CarrotAndStick",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    public CarrotAndStick() {
        super(cardInfo);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MakeTempCardInDrawPileAction(new Carrot(),1, true, true));
        addToBot(new MakeTempCardInDrawPileAction(new Stick(),1,true,true));
    }

    @Override
    public AbstractCard makeCopy() {
        return new CarrotAndStick();
    }
}
