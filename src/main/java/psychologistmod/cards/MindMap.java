package psychologistmod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import psychologistmod.actions.MindMapAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.util.CardInfo;

import static psychologistmod.ThePsychologistMod.makeID;

public class MindMap extends BaseCard {
    private final static CardInfo cardInfo = new CardInfo(
            "MindMap",
            3,
            CardType.SKILL,
            CardTarget.SELF,
            CardRarity.UNCOMMON,
            ThePsychologist.Enums.CARD_COLOR);
    public static final String ID = makeID(cardInfo.baseId);

    public MindMap() {
        super(cardInfo);
        setMagic(0);
        setExhaust(true);
        setCostUpgrade(2);
    }

    public void applyPowers() {
        this.baseMagicNumber = AbstractDungeon.player.hand.size() - 1;
        super.applyPowers();
        this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MindMapAction());
    }

    @Override
    public AbstractCard makeCopy() {
        return new MindMap();
    }
}
