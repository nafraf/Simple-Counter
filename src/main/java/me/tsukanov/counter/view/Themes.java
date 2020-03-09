package me.tsukanov.counter.view;

import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import me.tsukanov.counter.R;
import me.tsukanov.counter.SharedPrefKeys;

public enum Themes {
  LIGHT("light", R.string.settings_theme_light),
  DARK("dark", R.string.settings_theme_dark),
  AUTO("auto", R.string.settings_theme_auto_battery),
  SYSTEM("system", R.string.settings_theme_system);

  @NonNull private final String identifier;
  private final int labelId;

  Themes(final @NonNull String identifier, final int labelId) {
    this.identifier = identifier;
    this.labelId = labelId;
  }

  @NonNull
  private String getIdentifier() {
    return identifier;
  }

  public int getLabelId() {
    return labelId;
  }

  @NonNull
  public static Themes findOrGetDefault(final @Nullable String identifier) {
    if (identifier != null) {
      for (final Themes t : values()) {
        if (t.getIdentifier().equals(identifier)) return t;
      }
    }
    return LIGHT;
  }

  /** @param sharedPrefs {@link SharedPreferences} that contain the theme preference. */
  public static void initCurrentTheme(@NonNull final SharedPreferences sharedPrefs) {
    final Themes currentTheme =
        Themes.findOrGetDefault(sharedPrefs.getString(SharedPrefKeys.THEME.getName(), null));

    switch (currentTheme) {
      case LIGHT:
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        break;
      case DARK:
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        break;
      case AUTO:
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
        break;
      case SYSTEM:
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        break;
    }
  }
}
