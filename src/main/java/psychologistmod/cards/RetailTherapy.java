package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import psychologistmod.actions.PsychologistChannelAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.orbs.Academic;
import psychologistmod.orbs.Therapeutic;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class RetailTherapy extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "RetailTherapy",
            2,
            CardType.ATTACK,
            CardTarget.ENEMY,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int DAMAGE = 3;

    private static final int MAGIC = 2;
    public static final int UPG_MAGIC = 1;

    public RetailTherapy() {
        super(cardInfo);
        setDamage(DAMAGE);
        setMagic(MAGIC, UPG_MAGIC);
    }

    public void moreDamage(int price){
        baseDamage++;
        applyPowers();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RetailTherapy();
    }
}
