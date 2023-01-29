package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import psychologistmod.interfaces.OnPlayerBlockBrokenPower;

@SpirePatch(
        clz=AbstractCreature.class,
        method="brokeBlock"
)
public class OnPlayerBlockBrokenPowerPatch
{
    public static void Prefix(AbstractCreature __instance)
    {
        if(__instance instanceof AbstractPlayer){
            for (AbstractMonster mo : (AbstractDungeon.getCurrRoom()).monsters.monsters) {
                for(AbstractPower power : mo.powers)
                    if (power instanceof OnPlayerBlockBrokenPower) {
                        ((OnPlayerBlockBrokenPower)power).onPlayerBlockBroken();
                    }
            }
        }
    }
}