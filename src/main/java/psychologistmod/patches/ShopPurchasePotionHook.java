package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import psychologistmod.ThePsychologistMod;

@SpirePatch(cls="com.megacrit.cardcrawl.shop.StorePotion", method="purchasePotion")
public class ShopPurchasePotionHook {
    @SpireInsertPatch(rloc=6,localvars= {"price"})
    public static void Insert(int price) {
        ThePsychologistMod.publishPostShopPurchase(price);
    }
}