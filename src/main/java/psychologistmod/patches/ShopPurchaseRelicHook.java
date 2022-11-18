package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import psychologistmod.ThePsychologistMod;

@SpirePatch(cls="com.megacrit.cardcrawl.shop.StoreRelic", method="purchaseRelic")
public class ShopPurchaseRelicHook {
    @SpireInsertPatch(rloc=3,localvars= {"price"})
    public static void Insert(int price) {
        ThePsychologistMod.publishPostShopPurchase(price);
    }
}