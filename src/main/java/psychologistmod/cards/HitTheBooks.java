package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.actions.PsychologistChannelAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.orbs.Academic;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class HitTheBooks extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HitTheBooks",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 14;
    private static final int UPG_DAMAGE = 5;

    public HitTheBooks() {
        super(cardInfo);
        setDamage(DAMAGE, UPG_DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new PsychologistChannelAction(new Academic()));
    }

    @Override
    public AbstractCard makeCopy() {
        return new HitTheBooks();
    }
}
