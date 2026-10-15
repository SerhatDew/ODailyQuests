package com.ordwen.odailyquests.reload;

import com.ordwen.odailyquests.ODailyQuests;
import com.ordwen.odailyquests.files.FilesManager;
import com.ordwen.odailyquests.api.events.ODQReloadEvent;
import com.ordwen.odailyquests.configuration.ConfigFactory;
import com.ordwen.odailyquests.quests.player.progression.storage.DatabaseManager;
import org.mockito.MockedStatic;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

import static org.mockito.Mockito.*;

class ReloadServiceTest {
    @Test
    void disablesPluginWithoutPublishingDatabaseLoadedWhenStorageFails() {
        ODailyQuests plugin = mock(ODailyQuests.class);
        FilesManager files = mock(FilesManager.class);
        DatabaseManager database = mock(DatabaseManager.class);
        Server server = mock(Server.class);
        PluginManager plugins = mock(PluginManager.class);
        when(plugin.getFilesManager()).thenReturn(files);
        when(plugin.getDatabaseManager()).thenReturn(database);
        when(plugin.getServer()).thenReturn(server);
        when(server.getPluginManager()).thenReturn(plugins);
        when(plugin.getLogger()).thenReturn(Logger.getAnonymousLogger());
        doThrow(new IllegalStateException("Database unavailable")).when(database).load();

        try (MockedStatic<ConfigFactory> configs = mockStatic(ConfigFactory.class);
             MockedStatic<ODQReloadEvent> events = mockStatic(ODQReloadEvent.class)) {
            new ReloadService(plugin).reload();

            verify(plugins).disablePlugin(plugin);
            events.verify(() -> ODQReloadEvent.call(plugin, ReloadPhase.DATABASE_LOADED), never());
            events.verify(() -> ODQReloadEvent.call(plugin, ReloadPhase.RELOAD_COMPLETE), never());
        }
    }

    @Test
    void disablesPluginWhenStartupCannotReachDatabaseInitialization() {
        ODailyQuests plugin = mock(ODailyQuests.class);
        FilesManager files = mock(FilesManager.class);
        Server server = mock(Server.class);
        PluginManager plugins = mock(PluginManager.class);
        when(plugin.getFilesManager()).thenReturn(files);
        when(plugin.getServer()).thenReturn(server);
        when(server.getPluginManager()).thenReturn(plugins);
        when(plugin.getLogger()).thenReturn(Logger.getAnonymousLogger());
        doThrow(new IllegalArgumentException("Invalid configuration")).when(files).load();

        new ReloadService(plugin).reload();

        verify(plugins).disablePlugin(plugin);
        verify(plugin, never()).getDatabaseManager();
    }
}
