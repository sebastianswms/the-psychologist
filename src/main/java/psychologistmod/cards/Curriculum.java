package psychologistmod.cards;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.orbs.DoctrineOrb;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class Curriculum extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "Curriculum",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.COMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;

    public Curriculum() {
        super(cardInfo);

        setBlock(BLOCK, UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        for (AbstractOrb o : AbstractDungeon.player.orbs) {
            if (o instanceof DoctrineOrb) {
                addToBot(new GainEnergyAction(2));
                break;
            }
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Curriculum();
    }
}
