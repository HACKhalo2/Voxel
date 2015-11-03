package net.guerra24.voxel.core;

import java.util.Random;

import net.guerra24.voxel.api.API;
import net.guerra24.voxel.graphics.opengl.Display;
import net.guerra24.voxel.input.Keyboard;
import net.guerra24.voxel.menu.MainMenu;
import net.guerra24.voxel.menu.OptionsMenu;
import net.guerra24.voxel.menu.PauseMenu;
import net.guerra24.voxel.resources.GameResources;
import net.guerra24.voxel.resources.Loader;
import net.guerra24.voxel.util.vector.Vector3f;
import net.guerra24.voxel.world.WorldsHandler;

public class GlobalStates {

	public boolean loop = false;
	private GameState state;

	private MainMenu mainMenu;
	private PauseMenu pauseMenu;
	private OptionsMenu optionsMenu;

	public enum GameState {
		GAME_SP, MAINMENU, IN_PAUSE, LOADING_WORLD, OPTIONS;
	}

	public GlobalStates(Loader loader) {
		mainMenu = new MainMenu(loader);
		pauseMenu = new PauseMenu();
		optionsMenu = new OptionsMenu();
		loop = true;
		state = GameState.MAINMENU;
	}

	public void updateUpdateThread(GameResources gm, WorldsHandler worlds, API api, Display display) {

		if (state == GameState.MAINMENU && mainMenu.getPlayButton().pressed()) {
			state = GameState.LOADING_WORLD;
			Random seed;
			if (VoxelVariables.isCustomSeed) {
				seed = new Random(VoxelVariables.seed.hashCode());
			} else {
				seed = new Random();
			}
			worlds.getActiveWorld().startWorld("World-0", seed, 0, api, gm);
			gm.getCamera().setMouse();
			gm.getSoundSystem().stop("menu1");
			gm.getSoundSystem().rewind("menu1");
			state = GameState.GAME_SP;
		}

		if (state == GameState.MAINMENU && mainMenu.getExitButton().pressed()) {
			loop = false;
		}

		if (state == GameState.IN_PAUSE && pauseMenu.getBackToMain().pressed()) {
			worlds.getActiveWorld().clearDimension(gm);
			gm.getSoundSystem().play("menu1");
			gm.getCamera().setPosition(new Vector3f(0, 0, 1));
			gm.getCamera().setPitch(0);
			gm.getCamera().setYaw(0);
			state = GameState.MAINMENU;
			gm.getSoundSystem().setVolume("menu1", 1f);
		}

		if (state == GameState.MAINMENU && mainMenu.getOptionsButton().pressed()) {
			gm.getCamera().setPosition(new Vector3f(-1.4f, -3.4f, 1.4f));
			state = GameState.OPTIONS;
		}

		if (state == GameState.OPTIONS && optionsMenu.getExitButton().pressed()) {
			gm.getCamera().setPosition(new Vector3f(0, 0, 1));
			state = GameState.MAINMENU;
		}
		if (state == GameState.MAINMENU) {
			if (mainMenu.getPlayButton().insideButton())
				mainMenu.getList().get(0).changeScale(0.074f);
			else
				mainMenu.getList().get(0).changeScale(0.07f);
			if (mainMenu.getExitButton().insideButton())
				mainMenu.getList().get(2).changeScale(0.074f);
			else
				mainMenu.getList().get(2).changeScale(0.07f);
			if (mainMenu.getOptionsButton().insideButton())
				mainMenu.getList().get(1).changeScale(0.074f);
			else
				mainMenu.getList().get(1).changeScale(0.07f);

		}

		if (state == GameState.OPTIONS) {
			if (optionsMenu.getExitButton().insideButton())
				mainMenu.getList().get(3).changeScale(0.074f);
			else
				mainMenu.getList().get(3).changeScale(0.07f);
		}

		if (state == GameState.GAME_SP && !display.isDisplayFocused() && !VoxelVariables.debug) {
			gm.getCamera().unlockMouse();
			state = GameState.IN_PAUSE;
		}

		if (Display.isCloseRequested())
			loop = false;

		while (Keyboard.next()) {
			if (state == GameState.GAME_SP && Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				gm.getCamera().unlockMouse();
				state = GameState.IN_PAUSE;
			} else if (state == GameState.IN_PAUSE && Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				gm.getCamera().setMouse();
				state = GameState.GAME_SP;
			}
		}
	}

	public void updateRenderThread(GameResources gm, WorldsHandler worlds, API api, Display display) {
	}

	public GameState getState() {
		return state;
	}

	public MainMenu getMainMenu() {
		return mainMenu;
	}

}
