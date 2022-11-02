package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Defend_Maroon extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Defend_Maroon",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.BASIC,
            ThePsychologist.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public Defend_Maroon() {
        super(cardInfo);
        setBlock(BLOCK,UPG_BLOCK);
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Defend_Maroon();
    }
}