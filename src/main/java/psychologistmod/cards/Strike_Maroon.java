package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Strike_Maroon extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Strike_Maroon",
            1,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.BASIC,
            ThePsychologist.Enums.CARD_COLOR);

    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 6;
    private static final int UPG_DAMAGE = 3;

    public Strike_Maroon() {
        super(cardInfo);
        setDamage(DAMAGE,UPG_DAMAGE);
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Strike_Maroon();
    }
}