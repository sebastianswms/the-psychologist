package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import psychologistmod.ThePsychologistMod;

@SpirePatch(cls="com.megacrit.cardcrawl.shop.ShopScreen", method="updatePurge")
public class ShopPurchasePurgeHook {
    @SpireInsertPatch(rloc=4,localvars= {"actualPurgeCost"})
    public static void Insert(int actualPurgeCost) {
        ThePsychologistMod.publishPostShopPurchase(actualPurgeCost);
    }
}