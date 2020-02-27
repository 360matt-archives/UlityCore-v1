package fr.ulity.core.api.adapter;

import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.DatabaseMetaData;
import java.sql.Connection;
import java.sql.SQLException;

public class SQL {
    public static boolean tableExists(@NotNull final String table) {
        try {
            final Connection connection = Connect_MySQL.getConnection();
            if (connection == null)
                return false;
            final DatabaseMetaData metadata = connection.getMetaData();
            if (metadata == null)
                return false;
            final ResultSet rs = metadata.getTables(null, null, table, null);
            if (rs.next())
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean exists(@NotNull final String key, @NotNull final String table) {
        try {
            final ResultSet rs = Connect_MySQL.query("SELECT * FROM " + table + " WHERE `id` = " + key + ";");
            if (rs.next())
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    public static boolean deleteTable(final String table) {
        return Connect_MySQL.update("DROP TABLE " + table + ";");
    }

    public static boolean clearTable(final String table) {
        return Connect_MySQL.update("TRUNCATE TABLE " + table + ";");
    }

    public static boolean createTable(@NotNull final String table) {
        boolean response = Connect_MySQL.update("CREATE TABLE IF NOT EXISTS `" + table + "` ( `id` VARCHAR(100) NOT NULL , `value` LONGTEXT NOT NULL , UNIQUE (`id`));");
        return !tableExists(table) && response;
    }

    public static boolean set(@NotNull final String key, final Object value, @NotNull final String table) {
        return Connect_MySQL.update("INSERT INTO `" + table +
                "` (`id`, `value`) " +
                "VALUES (\"" + key + "\", \"" + value + "\") " +
                "ON DUPLICATE KEY UPDATE `value`= \"" + value + "\"");
    }

    public static boolean delete(@NotNull final String key, @NotNull final String table) {
        return Connect_MySQL.update("DELETE FROM `" + table + "`\n" +
                "WHERE `id` = \"" + key + "\"");
    }

    public static Object getObject(@NotNull final String key, @NotNull final String table) {
        ResultSet rs = Connect_MySQL.query("SELECT * FROM `" + table + "` WHERE `id` = \"" + key + "\"");

        try {
            if (rs.next()) {
                return rs.getObject("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getString(@NotNull final String key, @NotNull final String table) {
        ResultSet rs = Connect_MySQL.query("SELECT * FROM `" + table + "` WHERE `id` = \"" + key + "\"");

        try {
            if (rs.next()) {
                return rs.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int getInt(@NotNull final String key, @NotNull final String table) {
        ResultSet rs = Connect_MySQL.query("SELECT * FROM `" + table + "` WHERE `id` = \"" + key + "\"");

        try {
            if (rs.next()) {
                return rs.getInt("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}