package psychologistmod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.patches.PsychologistModTagsPatch;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class SelfCare extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SelfCare",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public SelfCare() {
        super(cardInfo);
        setMagic(MAGIC,UPG_MAGIC);
        tags.add(PsychologistModTagsPatch.ACTUALIZATION);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AddTemporaryHPAction(p,p,magicNumber));
        if(p.currentBlock > 0){
            addToBot(new DrawCardAction(p,1));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SelfCare();
    }
}