package psychologistmod.cards;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.evacipated.cardcrawl.mod.stslib.patches.core.AbstractCreature.TempHPField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.powers.FeedbackPower;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class HierarchyOfNeeds extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "HierarchyOfNeeds",
            2,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.RARE,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 3;

    private static final int MAGIC = 10;
    private static final int UPG_MAGIC = 3;

    public HierarchyOfNeeds() {
        super(cardInfo);
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(MAGIC,UPG_MAGIC);
        setExhaust(true);
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(p.currentBlock != 0 && TempHPField.tempHp.get(p) != 0){
            addToBot(new HealAction(p,p,magicNumber));
        }else if(p.currentBlock != 0){
            addToBot(new AddTemporaryHPAction(p,p,magicNumber));
        }else{
            addToBot(new GainBlockAction(p,p,block));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HierarchyOfNeeds();
    }
}
