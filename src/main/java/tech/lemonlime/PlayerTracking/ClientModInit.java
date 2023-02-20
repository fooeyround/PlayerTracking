package tech.lemonlime.PlayerTracking;


import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.item.CompassAnglePredicateProvider;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import tech.lemonlime.PlayerTracking.item.FindingCompassItem;
import tech.lemonlime.PlayerTracking.registry.ModItems;

public class ClientModInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModelPredicateProviderRegistry.register(ModItems.FINDING_COMPASS, new Identifier("angle"), new CompassAnglePredicateProvider((world, stack, entity) ->
                FindingCompassItem.hasLodestone(stack) ? FindingCompassItem.createLodestonePos(stack.getOrCreateNbt()) : FindingCompassItem.createSpawnPos(world)));

    }
}
