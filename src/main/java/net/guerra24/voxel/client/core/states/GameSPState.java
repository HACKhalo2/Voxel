package net.guerra24.voxel.client.core.states;

import net.guerra24.voxel.client.api.API;
import net.guerra24.voxel.client.core.GlobalStates;
import net.guerra24.voxel.client.core.GlobalStates.GameState;
import net.guerra24.voxel.client.core.State;
import net.guerra24.voxel.client.core.Voxel;
import net.guerra24.voxel.client.core.VoxelVariables;
import net.guerra24.voxel.client.graphics.opengl.Display;
import net.guerra24.voxel.client.particle.ParticleMaster;
import net.guerra24.voxel.client.resources.GameResources;
import net.guerra24.voxel.client.resources.GuiResources;
import net.guerra24.voxel.client.world.WorldsHandler;

/**
 * Single Player GameState
 * 
 * @author danirod
 * @category Kernel
 */
public class GameSPState implements State {

	@Override
	public void update(Voxel voxel, GlobalStates states, float delta) {
		GameResources gm = voxel.getGameResources();
		GuiResources gi = voxel.getGuiResources();
		WorldsHandler worlds = voxel.getWorldsHandler();
		API api = voxel.getApi();
		Display display = voxel.getDisplay();

		worlds.getActiveWorld().updateChunksGeneration(gm, api, delta);
		gm.getPhysics().getMobManager().update(delta, gm, gi, worlds.getActiveWorld(), api);
		gm.update(gm.getSkyboxRenderer().update(delta));
		gm.getRenderer().getWaterRenderer().update(delta);
		ParticleMaster.getInstance().update(delta);

		if (!display.isDisplayFocused() && !VoxelVariables.debug) {
			gm.getCamera().unlockMouse();
			states.state = GameState.IN_PAUSE;
		}
	}

	@Override
	public void render(Voxel voxel, GlobalStates states, float delta) {
		GameResources gm = voxel.getGameResources();
		WorldsHandler worlds = voxel.getWorldsHandler();
		API api = voxel.getApi();

		worlds.getActiveWorld().lighting();
		gm.getCamera().update(delta, gm, worlds.getActiveWorld(), api, voxel.getClient());
		gm.getPhysics().getMobManager().getPlayer().update(delta, gm, voxel.getGuiResources(), worlds.getActiveWorld(),
				api);
		gm.getFrustum().calculateFrustum(gm.getRenderer().getProjectionMatrix(), gm.getCamera());
		gm.getSun_Camera().setPosition(gm.getCamera().getPosition());
		if (VoxelVariables.useShadows) {
			gm.getMasterShadowRenderer().being();
			gm.getRenderer().prepare();
			worlds.getActiveWorld().updateChunksShadow(gm);
			gm.getMasterShadowRenderer().end();
		}
		gm.getDeferredShadingRenderer().getPost_fbo().begin();
		gm.getRenderer().prepare();
		gm.getRenderer().begin(gm);
		worlds.getActiveWorld().updateChunksRender(gm);
		gm.getRenderer().end(gm);
		gm.getSkyboxRenderer().render(VoxelVariables.RED, VoxelVariables.GREEN, VoxelVariables.BLUE, delta, gm);
		gm.getRenderer().renderEntity(gm.getPhysics().getMobManager().getMobs(), gm);
		ParticleMaster.getInstance().render(gm.getCamera());
		gm.getDeferredShadingRenderer().getPost_fbo().end();

		gm.getRenderer().prepare();
		gm.getDeferredShadingRenderer().render(gm);
	}

}
