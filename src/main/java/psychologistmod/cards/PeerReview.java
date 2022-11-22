package psychologistmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.actions.PeerReviewAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class PeerReview extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "PeerReview",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    public PeerReview() {
        super(cardInfo, true);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PeerReviewAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new PeerReview();
    }
}
