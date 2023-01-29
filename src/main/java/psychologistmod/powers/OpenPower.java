package psychologistmod.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnMyBlockBrokenPower;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static psychologistmod.ThePsychologistMod.makeID;

public class OpenPower extends BasePower implements CloneablePowerInterface, OnMyBlockBrokenPower {
    public static final String POWER_ID = makeID("Open");
    private static final AbstractPower.PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final boolean CAN_GO_NEGATIVE = false;

    public OpenPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, CAN_GO_NEGATIVE, owner, amount);
    }

    @Override
    public void onMyBlockBroken() {
        addToBot(new DrawCardAction(this.owner, this.amount));
    }

    public void updateDescription() {
        if(this.amount == 1){
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
            return;
        }
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }

    public void atEndOfRound() {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, OpenPower.POWER_ID));
    }

    @Override
    public AbstractPower makeCopy() {
        return new OpenPower(owner, amount);
    }
}
