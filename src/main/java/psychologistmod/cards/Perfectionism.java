package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Perfectionism extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Perfectionism",
            1,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 9;
    private static final int UPG_BLOCK = 4;

    private static final int MAGIC = 4;

    public Perfectionism() {
        super(cardInfo);
        setBlock(BLOCK, UPG_BLOCK);
        setMagic(MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if(p.currentHealth != p.maxHealth){
            addToBot(new ApplyPowerAction(p, p, new FeedbackPower(p, this.magicNumber), this.magicNumber));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Perfectionism();
    }
}
