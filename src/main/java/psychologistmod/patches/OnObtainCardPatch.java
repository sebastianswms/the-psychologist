package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;
import psychologistmod.interfaces.PsychologistOnObtainCard;

@SpirePatch(clz = ShowCardAndObtainEffect.class, method = "update")
public class OnObtainCardPatch {
    @SpirePostfixPatch
    public static void Postfix(ShowCardAndObtainEffect __instance, AbstractCard ___card) {
        if (__instance.isDone && ___card instanceof PsychologistOnObtainCard) {
            ((PsychologistOnObtainCard)___card).onObtainCard();
        }
    }
}