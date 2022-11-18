package psychologistmod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import psychologistmod.ThePsychologistMod;

@SpirePatch(cls="com.megacrit.cardcrawl.shop.ShopScreen", method="purchaseCard")
public class ShopPurchaseCardHook {
    @SpireInsertPatch(rloc=4,localvars= {"hoveredCard"})
    public static void Insert(AbstractCard hoveredCard) {
        ThePsychologistMod.publishPostShopPurchase(hoveredCard.price);
    }
}