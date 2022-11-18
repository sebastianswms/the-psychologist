package psychologistmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static psychologistmod.ThePsychologistMod.makeID;

public class PseudosciencePower extends BasePower implements CloneablePowerInterface {
    public static final String POWER_ID = makeID("Pseudoscience");
    private static final AbstractPower.PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = false;

    public PseudosciencePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, CAN_GO_NEGATIVE, owner, amount);
    }

    public void atStartOfTurn() {
        flash();
        addToBot(new ApplyPowerAction(this.owner, this.owner, new RigorPower(this.owner, -this.amount), -this.amount));
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new PseudosciencePower(owner, amount);
    }
}
