package psychologistmod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.common.DamageCallbackAction;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class MakeItPersonal extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MakeItPersonal",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 2;

    public MakeItPersonal() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageCallbackAction(
                m,
                new DamageInfo(p, damage),
                AbstractGameAction.AttackEffect.BLUNT_LIGHT,
                damageDone -> {
                    if (damageDone > 0) {
                        addToBot(new AddTemporaryHPAction(p, p, damageDone));
                    }
                }
        ));
    }

    @Override
    public AbstractCard makeCopy() {
        return new MakeItPersonal();
    }
}
