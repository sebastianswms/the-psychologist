package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.interfaces.PsychologistOnObtainCard;
import psychologistmod.util.CardInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static psychologistmod.ThePsychologistMod.makeID;

public class SelfImprovement extends BaseCard implements PsychologistOnObtainCard {
    private final static CardInfo cardInfo = new CardInfo(
            "SelfImprovement",
            2,
            CardType.POWER,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int MAGIC = 3;
    public static final int UPG_MAGIC = 1;

    public SelfImprovement() {
        super(cardInfo);
        setMagic(MAGIC, UPG_MAGIC);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
    }

    @Override
    public AbstractCard makeCopy() {
        return new SelfImprovement();
    }

    @Override
    public void onObtainCard() {
        ArrayList<AbstractCard> upgradableCards = new ArrayList<>();

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (c.canUpgrade() && (c.type == CardType.ATTACK || c.type == CardType.SKILL)) {
                upgradableCards.add(c);
            }
        }

        Collections.shuffle(upgradableCards, new Random(AbstractDungeon.miscRng.randomLong()));
        if (!upgradableCards.isEmpty()) {
            (upgradableCards.get(0)).upgrade();
            AbstractDungeon.player.bottledCardUpgradeCheck(upgradableCards.get(0));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect((upgradableCards.get(0)).makeStatEquivalentCopy()));
        }
    }
}
