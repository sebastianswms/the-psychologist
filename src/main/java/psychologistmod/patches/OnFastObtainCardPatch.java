package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.FastCardObtainEffect;
import psychologistmod.interfaces.PsychologistOnObtainCard;

@SpirePatch(clz = FastCardObtainEffect.class, method = "update")
public class OnFastObtainCardPatch {
    @SpirePostfixPatch
    public static void Postfix(FastCardObtainEffect __instance, AbstractCard ___card) {
        if (__instance.isDone && ___card instanceof PsychologistOnObtainCard) {
            ((PsychologistOnObtainCard)___card).onObtainCard();
        }
    }
}