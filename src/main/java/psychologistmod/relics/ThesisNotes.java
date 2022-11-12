package psychologistmod.relics;

import com.megacrit.cardcrawl.cards.AbstractCard;
import psychologistmod.actions.PsychologistChannelAction;
import psychologistmod.characters.ThePsychologist;
import psychologistmod.orbs.Academic;

import static psychologistmod.ThePsychologistMod.makeID;

public class ThesisNotes extends BaseRelic {
    private static final String NAME = "ThesisNotes"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.STARTER; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.FLAT; //The sound played when the relic is clicked.

    public ThesisNotes(String id, String imageName, AbstractCard.CardColor pool, RelicTier tier, LandingSound sfx) {
        super(id, imageName, pool, tier, sfx);
    }

    public ThesisNotes() {
        super(ID, NAME, ThePsychologist.Enums.CARD_COLOR, RARITY,SOUND);
    }

    @Override
    public void atBattleStartPreDraw(){
        addToBot(new PsychologistChannelAction(new Academic()));
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
