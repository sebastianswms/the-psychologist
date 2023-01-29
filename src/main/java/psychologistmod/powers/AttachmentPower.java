package psychologistmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import psychologistmod.interfaces.OnPlayerBlockBrokenPower;

import static psychologistmod.ThePsychologistMod.makeID;

public class AttachmentPower extends BasePower implements CloneablePowerInterface, OnPlayerBlockBrokenPower {
    public static final String POWER_ID = makeID("Attachment");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = false;

    public AttachmentPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, CAN_GO_NEGATIVE, owner, amount);
    }

    @Override
    public void onPlayerBlockBroken() {
        addToTop(new DamageAction(this.owner, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.POISON));
        flash();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AttachmentPower(owner, amount);
    }
}
