package fr.ulity.core.api.adapter;

import org.jetbrains.annotations.NotNull;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Exec_MySQL {

    private String table;
    private PreparedStatement statement_query;
    private PreparedStatement statement_set;
    private PreparedStatement statement_delete;

    public Exec_MySQL() {
        this.table = "minecraft";
        reload();
    }

    public Exec_MySQL(String table) {
        this.table = table.replaceAll("[^a-zA-Z0-9]", "");
        reload();
    }

    public void reload() {
        Connect_MySQL.connect();
        if (!Connect_MySQL.isConnected())
            return;

        createTable();
        initialize();
    }

    private boolean initialize() {
        try {
            if (statement_query == null) {
                String query = "select * from " + table + " where id = ?";
                statement_query = Connect_MySQL.con.prepareStatement(query);
                System.out.println(Connect_MySQL.con);
            }
            if (statement_set == null) {
                String query = "insert into " + table + " (`id`, `value`) VALUES (?, ?) on duplicate key update `value` = ?";
                statement_set = Connect_MySQL.con.prepareStatement(query);
            }
            if (statement_delete == null) {
                String query = "delete from " + table + " where `id` = ?";
                statement_delete = Connect_MySQL.con.prepareStatement(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean deleteTable() {
        return Connect_MySQL.update("DROP TABLE " + table + ";");
    }

    public boolean clearTable() {
        return Connect_MySQL.update("TRUNCATE TABLE " + table + ";");
    }

    public boolean set(@NotNull final String key, final Object value) {
        PreparedStatement statement = statement_set;
        try {
            statement.setString(1, key);
            statement.setObject(2, value);
            statement.setObject(3, value);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(@NotNull final String key) {
        PreparedStatement statement = statement_delete;
        try {
            statement.setString(1, key);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isSet(@NotNull final String key) {
        if (Connect_MySQL.isConnected()) {
            if (Connect_MySQL.connect()) {
                try {
                    ResultSet rs = getValue(key);

                    if (rs.next())
                        return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public boolean getBoolean(@NotNull final String key) {
        if (Connect_MySQL.isConnected()) {
            if (Connect_MySQL.connect()) {
                try {
                    ResultSet rs = getValue(key);

                    if (rs.next())
                        return rs.getBoolean("value");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public Object getObject(@NotNull final String key) {
        if (Connect_MySQL.isConnected()) {
            if (Connect_MySQL.connect()) {
                try {
                    ResultSet rs = getValue(key);

                    if (rs.next())
                        return rs.getObject("value");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public String getString(@NotNull final String key) {
        if (Connect_MySQL.isConnected()) {
            if (Connect_MySQL.connect()) {
                try {
                    ResultSet rs = getValue(key);

                    if (rs.next())
                        return rs.getString("value");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public int getInt(@NotNull final String key) {
        if (Connect_MySQL.isConnected()) {
            if (Connect_MySQL.connect()) {
                try {
                    ResultSet rs = getValue(key);

                    if (rs.next())
                        return rs.getInt("value");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0;
    }

    public Double getDouble(@NotNull final String key) {
        if (Connect_MySQL.isConnected()) {
            if (Connect_MySQL.connect()) {
                try {
                    ResultSet rs = getValue(key);

                    if (rs.next())
                        return rs.getDouble("value");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return 0D;
    }

    private ResultSet getValue(@NotNull final String key) {
        try {
            PreparedStatement statement = statement_query;
            statement.setString(1, key);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    private boolean tableExists() {
        try {
            if (Connect_MySQL.isConnected())
                return false;
            final DatabaseMetaData metadata = Connect_MySQL.con.getMetaData();
            if (metadata == null)
                return false;
            final ResultSet rs = metadata.getTables(null, null, table, null);
            if (rs.next())
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    private boolean createTable() {
        boolean response = Connect_MySQL.update("CREATE TABLE IF NOT EXISTS `" + table + "` ( `id` VARCHAR(100) NOT NULL , `value` LONGTEXT NOT NULL , UNIQUE (`id`));");
        return !tableExists() && response;
    }

}
