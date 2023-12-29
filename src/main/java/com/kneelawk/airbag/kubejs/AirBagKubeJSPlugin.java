package com.kneelawk.airbag.kubejs;

import appeng.core.definitions.AEItems;
import appeng.recipes.entropy.EntropyRecipeSerializer;
import appeng.recipes.game.FacadeRecipe;
import appeng.recipes.handlers.ChargerRecipeSerializer;
import appeng.recipes.handlers.InscriberRecipeSerializer;
import appeng.recipes.mattercannon.MatterCannonAmmoSerializer;
import appeng.recipes.transform.TransformRecipeSerializer;
import de.mari_023.ae2wtlib.wut.recipe.Combine;
import de.mari_023.ae2wtlib.wut.recipe.Upgrade;
import dev.latvian.mods.kubejs.KubeJSPlugin;
import dev.latvian.mods.kubejs.recipe.schema.JsonRecipeSchemaType;
import dev.latvian.mods.kubejs.recipe.schema.RecipeNamespace;
import dev.latvian.mods.kubejs.recipe.schema.RegisterRecipeSchemasEvent;

import org.quiltmc.loader.api.QuiltLoader;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;

public class AirBagKubeJSPlugin extends KubeJSPlugin {
    @Override
    public void registerRecipeSchemas(RegisterRecipeSchemasEvent event) {
        if (QuiltLoader.isModLoaded("ae2")) {
            RecipeNamespace ae2 = event.namespace("ae2");
            ae2.put("charger",
                new JsonRecipeSchemaType(ae2, new Identifier("ae2:charger"), ChargerRecipeSerializer.INSTANCE));
            ae2.put("inscriber",
                new JsonRecipeSchemaType(ae2, new Identifier("ae2:inscriber"), InscriberRecipeSerializer.INSTANCE));
            ae2.put("transform",
                new JsonRecipeSchemaType(ae2, new Identifier("ae2:transform"), TransformRecipeSerializer.INSTANCE));
            ae2.put("entropy",
                new JsonRecipeSchemaType(ae2, new Identifier("ae2:entropy"), EntropyRecipeSerializer.INSTANCE));
            ae2.put("matter_cannon", new JsonRecipeSchemaType(ae2, new Identifier("ae2:matter_cannon"),
                MatterCannonAmmoSerializer.INSTANCE));
            ae2.put("facade", new JsonRecipeSchemaType(ae2, new Identifier("ae2:facade"), null) {
                @Override
                public RecipeSerializer<?> getSerializer() {
                    // delay until after AE2 is loaded
                    return FacadeRecipe.getSerializer(AEItems.FACADE.asItem());
                }
            });
        }
        if (QuiltLoader.isModLoaded("ae2wtlib")) {
            RecipeNamespace ae2wt = event.namespace("ae2wtlib");
            ae2wt.put("upgrade", new JsonRecipeSchemaType(ae2wt, new Identifier("ae2wtlib:upgrade"), null) {
                @Override
                public RecipeSerializer<?> getSerializer() {
                    return Upgrade.serializer;
                }
            });
            ae2wt.put("combine", new JsonRecipeSchemaType(ae2wt, new Identifier("ae2wtlib:combine"), null) {
                @Override
                public RecipeSerializer<?> getSerializer() {
                    return Combine.serializer;
                }
            });
        }
    }
}
