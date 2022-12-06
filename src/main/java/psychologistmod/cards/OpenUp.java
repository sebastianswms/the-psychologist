package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.powers.OpenPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class OpenUp extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "OpenUp",
            1,
            CardType.ATTACK,
            CardTarget.SELF_AND_ENEMY,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 9;
    private static final int UPG_DAMAGE = 1;

    private static final int MAGIC = 1;
    private static final int UPG_MAGIC = 1;

    public OpenUp() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
        setMagic(MAGIC,UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        addToBot(new ApplyPowerAction(p, p, new OpenPower(p, magicNumber), magicNumber, false, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public AbstractCard makeCopy() {
        return new OpenUp();
    }
}
