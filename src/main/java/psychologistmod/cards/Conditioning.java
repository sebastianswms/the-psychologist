package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.evacipated.cardcrawl.mod.stslib.cards.targeting.SelfOrEnemyTargeting;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Conditioning extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Conditioning",
            0,
            CardType.SKILL,
            SelfOrEnemyTargeting.SELF_OR_ENEMY,
            CardRarity.SPECIAL,
            CardColor.COLORLESS);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 3;

    public Conditioning() {
        super(cardInfo, true);
        setMagic(MAGIC);
        setExhaust(true);
        setSelfRetain(false,true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = SelfOrEnemyTargeting.getTarget(this);

        if (target == null) {
            target = AbstractDungeon.player;
        }

        addToBot(new ApplyPowerAction(target, p, new FeedbackPower(target, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(target, p, new StrengthPower(target, magicNumber), magicNumber));
        addToBot(new ApplyPowerAction(target, p, new LoseStrengthPower(target, magicNumber), magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Conditioning();
    }
}
