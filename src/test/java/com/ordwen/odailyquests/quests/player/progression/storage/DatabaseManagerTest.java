package com.ordwen.odailyquests.quests.player.progression.storage;

import com.ordwen.odailyquests.configuration.essentials.Database;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

class DatabaseManagerTest {
    @Test
    void propagatesInitializationFailureInsteadOfReportingSuccess() {
        IllegalArgumentException failure = new IllegalArgumentException("Invalid storage configuration");
        try (MockedStatic<Database> database = mockStatic(Database.class)) {
            database.when(Database::getMode).thenThrow(failure);
            DatabaseManager manager = new DatabaseManager(null);

            IllegalStateException exception = assertThrows(IllegalStateException.class, manager::load);

            assertSame(failure, exception.getCause());
            assertNull(manager.getSqlManager());
            assertNull(manager.getYamlManager());
        }
    }
}
