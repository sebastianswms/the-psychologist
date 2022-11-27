package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class SelfRestraint extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SelfRestraint",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 0;

    private static final int MAGIC = 4;
    private static final int UPG_MAGIC = 2;

    public SelfRestraint() {
        super(cardInfo);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SelfRestraint();
    }
}
