package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.actions.PsychologistChannelAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.orbs.Therapeutic;
import psychologistmod.patches.PsychologistModTagsPatch;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class SelfAcceptance extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SelfAcceptance",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public SelfAcceptance() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
        tags.add(PsychologistModTagsPatch.ACTUALIZATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new PsychologistChannelAction(new Therapeutic()));
        addToBot(new DrawCardAction(p, magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SelfAcceptance();
    }
}
