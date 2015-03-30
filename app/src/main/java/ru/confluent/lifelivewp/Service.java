package ru.confluent.lifelivewp;

import android.content.Context;

import rajawali.wallpaper.Wallpaper;

public class Service extends Wallpaper {
	private Renderer mRenderer;

	public Engine onCreateEngine() {
		mRenderer = new Renderer(this);
		return new WallpaperEngine(this.getSharedPreferences(SHARED_PREFS_NAME,
				Context.MODE_PRIVATE), getBaseContext(), mRenderer, false);
	}
}